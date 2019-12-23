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
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate redisTemplate;

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
                String subject = claims.getSubject();

                ProblemEntity problemEntity = problemService.selectById(entity.getProblemid());
                if (problemEntity == null){
                    return new Result(false,StatusCode.FAIL,"该问题已被删除");
                }

                problemEntity.setReply(problemEntity.getReply()+1);
                entity.setUserid(id);
                entity.setNickname(subject);
                replyService.add(entity);
                problemService.update(problemEntity);

                return new Result(true,StatusCode.OK,"回答成功");

            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,StatusCode.LOGIN_ERROR,"登录失效，请重新登录");
            }
        }
        return new Result(false,StatusCode.LOGIN_ERROR,"请先登录");
    }

    @GetMapping("/rate/{id}")
    public Result rate(@PathVariable("id") String id, HttpServletRequest request, Double rate){
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)){
            try{
                Claims claims = jwtUtil.parseToken(token);
                String userId = claims.getId();

                ReplyEntity replyEntity = replyService.selectById(id);
                if (replyEntity != null){
                    double sumRate = replyEntity.getRate() * replyEntity.getRateCount();
                    replyEntity.setRate((sumRate + rate) / (replyEntity.getRateCount() + 1));
                    replyEntity.setRateCount(replyEntity.getRateCount() + 1);
                    replyService.update(replyEntity);
                    redisTemplate.opsForSet().add("problem:reply:rate:" + id, userId);
                    return new Result(true,StatusCode.OK,"评分成功");
                }else {
                    return new Result(false,StatusCode.FAIL,"该回答已被删除");
                }
            }catch (Exception e){
                return new Result(false,StatusCode.LOGIN_ERROR,"登录失效，请重新登录");
            }
        }
        return new Result();
    }
}
