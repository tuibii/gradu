package com.gradu.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class,args);
    }
}
