package com.gradu.user.service.impl;

import com.google.code.kaptcha.Producer;
import com.gradu.user.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    Producer producer;

    /**
     * Local Cache  5分钟过期
     */
    Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();


    @Override
    public BufferedImage create(String uuid) {
        //生成文字验证码
        String code = producer.createText();

        //保存到缓存
        setCache(uuid, code);

        return producer.createImage(code);
    }

    private void setCache(String uuid, String code) {
        localCache.put(uuid, code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        //获取验证码
        String captcha = getCache(uuid);

        //效验成功
        if(code.equalsIgnoreCase(captcha)){
            return true;
        }

        return false;
    }

    private String getCache(String uuid) {
        String captcha = localCache.getIfPresent(uuid);
        //删除验证码
        if(captcha != null){
            localCache.invalidate(uuid);
        }
        return captcha;
    }
}
