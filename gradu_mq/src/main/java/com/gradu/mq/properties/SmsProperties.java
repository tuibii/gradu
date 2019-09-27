package com.gradu.mq.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "sms.aliyun")
public class SmsProperties {

    private String AccessKeyId;

    private String AccessKeySecret;

    private String SignName;

    private String TemplateCode;
}
