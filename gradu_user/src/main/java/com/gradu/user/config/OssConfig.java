package com.gradu.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "oss.config")
public class OssConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String domain;

    private String endPoint;

    private String bucketName;
}
