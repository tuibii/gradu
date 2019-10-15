package com.gradu.friend.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/like/friend/{id}/{type}")
    public Result addFriend(@PathVariable("id") String friendid,@PathVariable("type") String type){

        Claims claims = (Claims) request.getAttribute("Authorization");

        if (claims == null){
            return new Result(false, StatusCode.FAIL,"未登录");
        }

        String userid = claims.getId();
        if (type != null && type.equals("1")){
            int num = friendService.add(userid,friendid);
            if (num != 0){
                return new Result(true,StatusCode.OK,"添加成功");
            }else {
                return new Result(false,StatusCode.FAIL,"不能重复添加");
            }
        }else {

        }

        return new Result(false, StatusCode.FAIL,"参数异常");
    }

}
