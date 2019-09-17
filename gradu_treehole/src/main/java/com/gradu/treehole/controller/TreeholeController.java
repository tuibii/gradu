package com.gradu.treehole.controller;

import com.gradu.treehole.service.TreeholeService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treehole")
@CrossOrigin
public class TreeholeController {

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",treeholeService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",treeholeService.selectById(id));
    }

    @GetMapping("/thumbup/{treeholeid}")
    public Result thumbup(@PathVariable("treeholeid") String treeholeid){

        /**
         *  待定
         */
        String userid = "wyl";

        if (redisTemplate.opsForSet().isMember("treehole:thumbup:"+treeholeid,userid)) {
            treeholeService.unthumbup(userid,treeholeid);
            return new Result(true,StatusCode.OK,"取消点赞成功");
        }else {
            treeholeService.thumbup(userid,treeholeid);
            return new Result(true,StatusCode.OK,"点赞成功");
        }

    }

}
