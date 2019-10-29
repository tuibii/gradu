package com.gradu.gathering.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.gathering.entity.GatheringEntity;
import com.gradu.gathering.service.GatheringService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    GatheringService gatheringService;

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        int page = (int) params.get("page");
        int size = (int) params.get("limit");
        Page<GatheringEntity> iPage = new Page<>(page,size);
        IPage<GatheringEntity> gatheringEntityIPage = gatheringService.page(iPage);
        return new Result(true, StatusCode.OK,"查询成功",gatheringEntityIPage);
    }

}
