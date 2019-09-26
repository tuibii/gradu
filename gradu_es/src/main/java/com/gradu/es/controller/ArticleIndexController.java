package com.gradu.es.controller;

import com.gradu.es.entity.ArticleIndexEntity;
import com.gradu.es.service.ArticleIndexService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleIndexController {

    @Autowired
    ArticleIndexService articleIndexService;

    @PostMapping
    public Result save(@RequestBody ArticleIndexEntity articleIndexEntity){
        articleIndexService.save(articleIndexEntity);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @GetMapping("/{key}/{page}/{size}")
    public Result selectByKey(@PathVariable("key")String key,@PathVariable("page") int page,@PathVariable("size") int size){
        Page<ArticleIndexEntity> entityPage = articleIndexService.selectByKey(key,page,size);
        PageResult<ArticleIndexEntity> pageResult = new PageResult<>();
        pageResult.setTotal(entityPage.getTotalElements());
        pageResult.setRows(entityPage.getContent());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

}
