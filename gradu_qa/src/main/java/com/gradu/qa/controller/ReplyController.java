package com.gradu.qa.controller;

import com.gradu.qa.entity.ReplyEntity;
import com.gradu.qa.service.ReplyService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @GetMapping("/problem/{id}")
    public Result getByProblemId(@PathVariable("id") String id){
        List<ReplyEntity> replyEntityList = replyService.listByProblemId(id);
        return new Result(true, StatusCode.OK, "查询成功", replyEntityList);
    }

}
