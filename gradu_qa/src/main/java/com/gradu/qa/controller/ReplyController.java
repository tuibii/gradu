package com.gradu.qa.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.qa.dto.ReplyDTO;
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
import java.util.Map;

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

    @Autowired
    HttpServletRequest request;

    @GetMapping("/problem/{id}")
    public Result getByProblemId(@PathVariable("id") String id){
        List<ReplyDTO> replyDTOS = replyService.listByProblemId(id);

        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null){
            String userid = claims.getId();
            if (StringUtils.isNotEmpty(userid)){
                replyDTOS.forEach(item -> {
                    System.out.println(redisTemplate.opsForSet().members("problem:reply:rate:" + item.getId()));
                    if (redisTemplate.opsForSet().isMember("problem:reply:rate:" + item.getId(),userid)) {
                        item.setCanRate(false);
                    }else {
                        item.setCanRate(true);
                    }
                });
            }
        }else {
            replyDTOS.forEach(item -> item.setCanRate(true));
        }

        return new Result(true, StatusCode.OK, "查询成功", replyDTOS);
    }

    @PostMapping
    public Result add(@RequestBody ReplyEntity entity){

        Claims claims = (Claims) request.getAttribute("claims");

        if (claims !=null){
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
        }

        return new Result(false,StatusCode.LOGIN_ERROR,"登录失效，请重新登录");
    }

    @PutMapping("/rate/{id}")
    public Result rate(@PathVariable("id") String id, @RequestBody ReplyDTO rate){
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null){
            String userId = claims.getId();

            ReplyEntity replyEntity = replyService.selectById(id);
            if (replyEntity != null){
                replyService.rate(userId,rate.getRate(),replyEntity);
                return new Result(true,StatusCode.OK,"评分成功");
            }else {
                return new Result(false,StatusCode.FAIL,"该回答已被删除");
            }
        }

        return new Result(false,StatusCode.LOGIN_ERROR,"登录失效，请重新登录");
    }

    @GetMapping("list")
    public Result list () {
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null) {
            QueryWrapper<ReplyEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("userid", claims.getId());
            List<ReplyEntity> list = replyService.list(wrapper);
            return new Result(true, StatusCode.OK, "查询成功", list);
        }

        return new Result(false, StatusCode.FAIL, "未登录");
    }
}
