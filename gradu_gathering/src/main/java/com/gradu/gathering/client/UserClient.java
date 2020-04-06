package com.gradu.gathering.client;

import com.gradu.gathering.entity.DynamicEntity;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gradu-user")
public interface UserClient {

    @PostMapping("/dynamic")
    Result save(@RequestBody DynamicEntity entity);
}
