package com.gradu.qa.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
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

    @GetMapping("/newproblem/{label}/{page}/{size}")
    public Result getNewList(@PathVariable("label") String label, @PathVariable("page") int page,@PathVariable("size") int size){

        Page<ProblemEntity> newProblem = problemService.getNewProblem(page, size, label);

        PageResult<ProblemEntity> pageResult = new PageResult<>();
        pageResult.setRows(newProblem.getRecords());
        pageResult.setTotal(newProblem.getTotal());

        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    @GetMapping("/hotproblem/{label}/{page}/{size}")
    public Result getHotList(@PathVariable("label") String label, @PathVariable("page") int page,@PathVariable("size") int size){

        Page<ProblemEntity> hotProblem = problemService.getHotProblem(page, size, label);

        PageResult<ProblemEntity> pageResult = new PageResult<>();
        pageResult.setRows(hotProblem.getRecords());
        pageResult.setTotal(hotProblem.getTotal());

        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    @PostMapping
    public Result add(@RequestBody ProblemEntity problemEntity){

        String role = (String) request.getAttribute("Authorization");

        if (StringUtils.isEmpty(role) || !role.equals("user")){
            return new Result(false,StatusCode.ACCESS_ERROR,"权限不足");
        }

        problemService.add(problemEntity);
        return new Result(true,StatusCode.OK,"添加成功");
    }

}
