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

    @GetMapping("/newproblem/{label}/{page}/{size}")
    public Result getNewList(@PathVariable("label") String label, @PathVariable("page") int page,@PathVariable("size") int size){

        Page<ProblemEntity> newProblem = problemService.getNewProblem(page, size, label);

        PageData<ProblemEntity> pageData = new PageData<>(newProblem.getRecords(),newProblem.getTotal());
        return new Result(true, StatusCode.OK,"查询成功", pageData);
    }

    @GetMapping("/hotproblem/{label}/{page}/{size}")
    public Result getHotList(@PathVariable("label") String label, @PathVariable("page") int page,@PathVariable("size") int size){

        Page<ProblemEntity> hotProblem = problemService.getHotProblem(page, size, label);

        PageData<ProblemEntity> pageData = new PageData<>(hotProblem.getRecords(),hotProblem.getTotal());
        return new Result(true, StatusCode.OK,"查询成功", pageData);
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
