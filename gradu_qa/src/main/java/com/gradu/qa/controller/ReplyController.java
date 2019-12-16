package com.gradu.qa.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.entity.ReplyEntity;
import com.gradu.qa.service.ProblemService;
import com.gradu.qa.service.ReplyService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    ProblemService problemService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/problem/{id}")
    public Result getByProblemId(@PathVariable("id") String id){
        List<ReplyEntity> replyEntityList = replyService.listByProblemId(id);
        return new Result(true, StatusCode.OK, "查询成功", replyEntityList);
    }

    @PostMapping
    public Result add(@RequestBody ReplyEntity entity, HttpServletRequest request){

        String token = request.getHeader("token");

        if (StringUtils.isNotEmpty(token)){
            try {
                Claims claims = jwtUtil.parseToken(token);
                String id = claims.getId();

                ProblemEntity problemEntity = problemService.selectById(entity.getProblemid());
                if (problemEntity == null){
                    return new Result(false,StatusCode.FAIL,"该问题已被删除");
                }


            }catch (Exception e){
                return new Result(false,StatusCode.LOGIN_ERROR,"登录失效，请重新登录");
            }
        }
        return new Result(false,StatusCode.LOGIN_ERROR,"请先登录");
    }
}
