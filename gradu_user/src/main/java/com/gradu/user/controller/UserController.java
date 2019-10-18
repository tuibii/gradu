package com.gradu.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.user.entity.UserEntity;
import com.gradu.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    HttpServletRequest request;

    /**
     *  短信验证
     * @return
     */
    @PostMapping("/send/{mobile}")
    public Result sendSms(@PathVariable("mobile")String mobile){

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(mobile),"mobile",mobile);
        UserEntity one = userService.getOne(wrapper);
        if (one !=null){
            return new Result(false,StatusCode.FAIL,"该账号已存在");
        }

        userService.sendSms(mobile);

        return new Result(true, StatusCode.OK,"发送成功");
    }

    /**
     *   注册
     * @param code
     * @param userEntity
     * @return
     */
    @PostMapping("/regist/{code}")
    public Result regist(@PathVariable("code") String code, @RequestBody UserEntity userEntity){

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(userEntity.getMobile()),"mobile",userEntity.getMobile());
        UserEntity one = userService.getOne(wrapper);
        if (one !=null){
            return new Result(false,StatusCode.FAIL,"该账号已存在");
        }

        String redisNumber = (String) redisTemplate.opsForValue().get("randomNumeric:"+userEntity.getMobile());

        if (redisNumber.isEmpty() || !redisNumber.equals(code)){
            return new Result(false,StatusCode.FAIL,"验证码错误");
        }

        userService.add(userEntity);

        return new Result(true,StatusCode.OK,"注册成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserEntity userEntity){

        UserEntity entity = userService.login(userEntity);

        if (entity == null){
            return new Result(false,StatusCode.LOGIN_ERROR,"登陆失败");
        }

        String token = jwtUtil.cteateToken(entity.getId(), entity.getNickname(), "user");
        return new Result(true,StatusCode.OK,"登陆成功",token);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id){

        Claims token = (Claims)request.getAttribute("token");
        String role = (String)token.get("role");

        if (StringUtils.isEmpty(role) || !role.equals("admin")){
            return new Result(false,StatusCode.ACCESS_ERROR,"权限不足");
        }

        userService.removeById(id);
        return new Result();
    }

    @PostMapping("/fans/{id}/{num}")
    public void incFans(@PathVariable("id") String id,@PathVariable("num") int num){
        userService.incFans(id,num);
    }

    @PostMapping("/follow/{id}/{num}")
    public void incFollow(@PathVariable("id") String id,@PathVariable("num") int num){
        userService.incFollow(id,num);
    }

}
