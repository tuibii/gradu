package com.gradu.mq.listener;

import com.aliyuncs.utils.StringUtils;
import com.gradu.mq.service.SmsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    SmsService smsProperties;

    @RabbitHandler
    public void excuteSms(Map<String,Object> map){

        String mobile = (String) map.get("mobile");

        String randomNumeric = (String) map.get("randomNumeric");

        if (StringUtils.isNotEmpty(mobile)&&StringUtils.isNotEmpty(randomNumeric)){
            smsProperties.sendRandomCodeSms(mobile,randomNumeric);
        }
    }

}
