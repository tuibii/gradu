package com.gradu.base.controller;


import com.gradu.base.dto.LabelDTO;
import com.gradu.base.serice.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    LabelService labelService;

    @GetMapping
    public Result getAllLabel(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.getAllLabel());
    }

    @GetMapping("/{id}")
    public Result getLabelById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.getLabelById(id));
    }

    @PostMapping
    public Result addLabel(@RequestBody LabelDTO labelDTO){
        labelService.addLabel(labelDTO);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/{id}")
    public Result updateLabel(@RequestBody LabelDTO labelDTO,@PathVariable("id") String id){
        labelDTO.setId(id);
        labelService.updateLabel(labelDTO);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delLabel(@PathVariable("id")String id){
        labelService.delLabelById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


}
