package com.gradu.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gradu-base")
public interface BaseClient {

    @GetMapping("/label/{id}")
    Result getLabelById(@PathVariable("id") String id);
}
