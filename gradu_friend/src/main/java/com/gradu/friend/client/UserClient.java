package com.gradu.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("gradu-user")
public interface UserClient {

    @PostMapping("/fans/{id}/{num}")
    void incFans(@PathVariable("id") String id, @PathVariable("num") int num);

    @PostMapping("/follow/{id}/{num}")
    void incFollow(@PathVariable("id") String id,@PathVariable("num") int num);

}
