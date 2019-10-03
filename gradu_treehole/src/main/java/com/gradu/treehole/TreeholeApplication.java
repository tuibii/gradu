package com.gradu.treehole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@EnableCaching
@SpringBootApplication
@EnableEurekaClient
public class TreeholeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreeholeApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

}
