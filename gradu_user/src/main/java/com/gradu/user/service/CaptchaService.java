package com.gradu.user.service;

import java.awt.image.BufferedImage;

public interface CaptchaService {
    /**
     * 图片验证码
     */
    BufferedImage create(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
