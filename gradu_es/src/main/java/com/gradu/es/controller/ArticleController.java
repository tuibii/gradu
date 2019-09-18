package com.gradu.es.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.es.entity.ArticleEntity;
import com.gradu.es.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody ArticleEntity articleEntity){
        articleService.save(articleEntity);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @GetMapping("/{key}/{page}/{size}")
    public Result selectByKey(@PathVariable("key")String key,@PathVariable("page") int page,@PathVariable("size") int size){
        Page<ArticleEntity> entityPage = articleService.selectById(key,page,size);
        PageResult<ArticleEntity> pageResult = new PageResult<>();
        pageResult.setTotal(entityPage.getTotal());
        pageResult.setRows(entityPage.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

}
