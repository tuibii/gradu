package com.gradu.qa.client;

import com.gradu.qa.entity.DynamicEntity;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gradu-user")
public interface UserClient {

    @PostMapping("/dynamic")
    Result save(@RequestBody DynamicEntity entity);
}
