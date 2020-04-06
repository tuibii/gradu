package com.gradu.user.controller;

import com.gradu.user.entity.DynamicEntity;
import com.gradu.user.service.DynamicService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dynamic")
@CrossOrigin
public class DynamicController {

    @Autowired
    DynamicService dynamicService;

    @PostMapping
    public Result save(@RequestBody DynamicEntity entity) {
        dynamicService.save(entity);
        return new Result(true, StatusCode.OK, "添加成功");
    }
}
