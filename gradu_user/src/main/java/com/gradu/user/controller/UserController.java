package com.gradu.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.user.entity.UserEntity;
import com.gradu.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

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

}
