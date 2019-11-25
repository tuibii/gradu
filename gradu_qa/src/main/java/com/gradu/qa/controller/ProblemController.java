package com.gradu.qa.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.qa.client.BaseClient;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import entity.PageData;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    BaseClient baseClient;

    @GetMapping("/label/{id}")
    public Result findlabel(@PathVariable("id") String id){
        return baseClient.getLabelById(id);
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<ProblemEntity> page = problemService.page(params);
        return new Result(true, StatusCode.OK,"查询成功",page);
    }

    @PostMapping
    public Result add(@RequestBody ProblemEntity problemEntity){

        Claims claims = (Claims) request.getAttribute("token");
        String role = (String) claims.get("role");

        if (StringUtils.isEmpty(role) || !role.equals("user")){
            return new Result(false,StatusCode.ACCESS_ERROR,"权限不足");
        }

        problemService.add(problemEntity);
        return new Result(true,StatusCode.OK,"添加成功");
    }

}
