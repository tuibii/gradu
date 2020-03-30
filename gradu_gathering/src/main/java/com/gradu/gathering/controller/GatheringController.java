package com.gradu.gathering.controller;

import com.gradu.gathering.entity.GatheringEntity;
import com.gradu.gathering.service.GatheringService;
import entity.PageData;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    GatheringService gatheringService;

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<GatheringEntity> page = gatheringService.page(params);
        return new Result(true, StatusCode.OK,"查询成功",page);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") String id){
        GatheringEntity gatheringEntity = gatheringService.selectById(id);
        return new Result(true, StatusCode.OK,"查询成功",gatheringEntity);
    }

    @PostMapping
    public Result save (@RequestBody GatheringEntity entity) {
        gatheringService.add(entity);
        return new Result(true, StatusCode.OK, "发布成功");
    }
}

