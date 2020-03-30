package com.gradu.gathering.service.impl;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSSClient;
import com.gradu.gathering.config.OssConfig;
import com.gradu.gathering.service.OssService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssConfig ossConfig;

    @Override
    public String upload(MultipartFile file) {
        OSSClient client = new OSSClient(ossConfig.getEndPoint(), ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());

        String path = getPath(file.getOriginalFilename());

        try {
            client.putObject(ossConfig.getBucketName(), path, file.getInputStream());
            client.shutdown();
        } catch (Exception e){
            e.printStackTrace();
        }

        return ossConfig.getDomain() + "/" + path;
    }

    private String getPath(String fileName) {
        String suffix = FilenameUtils.getExtension(fileName);

        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtil.format(new Date(), "yyyyMMdd") + "/" + uuid;

        return path + "." + suffix;
    }
}
