package com.gradu.gathering.client;

import com.gradu.gathering.entity.DynamicEntity;
import com.gradu.gathering.entity.UserEntity;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("gradu-user")
public interface UserClient {

    @PostMapping("/dynamic")
    Result save(@RequestBody DynamicEntity entity);

    @PostMapping("/user/userList")
    List<UserEntity> userList(@RequestBody List<String> userIds);
}
