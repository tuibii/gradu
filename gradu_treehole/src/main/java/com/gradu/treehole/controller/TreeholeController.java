package com.gradu.treehole.controller;

import com.gradu.treehole.entity.TreeholeEntity;
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
    public Result list(){
        return new Result(true, StatusCode.OK,"查询成功",treeholeService.list());
    }

    @GetMapping("/commentlist/{id}")
    public Result commentlist(@PathVariable("id")String id){
        return new Result(true,StatusCode.OK,"查询成功",treeholeService.findTreeholeEntityByParentid(id));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id){
        TreeholeEntity treeholeEntity = treeholeService.selectById(id);
        treeholeEntity.setThumbup(treeholeEntity.getThumbup()+1);
        treeholeService.update(treeholeEntity);
        return new Result(true,StatusCode.OK,"查询成功", treeholeEntity);
    }

    @PostMapping
    public Result insert(@RequestBody TreeholeEntity entity){
        if (entity.getParentid() != null){
            TreeholeEntity parentTreeHole = treeholeService.selectById(entity.getParentid());
            if (parentTreeHole != null){
                parentTreeHole.setComment(parentTreeHole.getComment()+1);
                treeholeService.update(parentTreeHole);
            }else {
                return new Result(false,StatusCode.FAIL,"该树洞不存在");
            }
        }
        treeholeService.add(entity);
        return new Result(true,StatusCode.OK,"发送成功");
    }

    @PutMapping
    public Result update(@RequestBody TreeholeEntity entity){
        treeholeService.update(entity);
        return new Result(true,StatusCode.OK,"修改");
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") String id){
        treeholeService.deleteById(id);
        return new Result(true,StatusCode.OK,"添加成功");
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
