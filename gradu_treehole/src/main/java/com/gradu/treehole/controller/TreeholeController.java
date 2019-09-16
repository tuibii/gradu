package com.gradu.treehole.controller;

import com.gradu.treehole.service.TreeholeService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treehole")
@CrossOrigin
public class TreeholeController {

    @Autowired
    TreeholeService treeholeService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",treeholeService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",treeholeService.selectById(id));
    }

}
