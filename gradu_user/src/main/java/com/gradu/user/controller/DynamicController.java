package com.gradu.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.user.entity.DynamicEntity;
import com.gradu.user.service.DynamicService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("dynamic")
@CrossOrigin
public class DynamicController {

    @Autowired
    DynamicService dynamicService;

    @Autowired
    HttpServletRequest request;

    @PostMapping
    public Result save(@RequestBody DynamicEntity entity) {
        dynamicService.save(entity);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("list")
    public Result list() {
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims == null) {
            return new Result(false, StatusCode.FAIL, "未登录");
        }
        QueryWrapper<DynamicEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", claims.getId());
        wrapper.orderByDesc("create_date");
        List<DynamicEntity> list = dynamicService.list(wrapper);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
