package com.gradu.base.controller;


import com.gradu.base.dto.LabelDTO;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @GetMapping
    public Result getAllLabel(){


        return new Result(true, StatusCode.OK,"查询成功");
    }

    @GetMapping("/{id}")
    public Result getLabelById(@PathVariable("id") String id){

        return new Result(true,StatusCode.OK,"查询成功");
    }

    @PostMapping
    public Result addLabel(@RequestBody LabelDTO labelDTO){

        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping
    public Result updateLabel(@RequestBody LabelDTO labelDTO){

        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delLabel(@PathVariable("id")String id){
        return new Result(true,StatusCode.OK,"删除成功");
    }


}
