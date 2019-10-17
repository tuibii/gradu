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

    @GetMapping("/focus/friend/{id}/{type}")
    public Result focusFriend(@PathVariable("id") String friendid,@PathVariable("type") String type){

        Claims claims = (Claims) request.getAttribute("Authorization");

        if (claims == null){
            return new Result(false, StatusCode.FAIL,"未登录");
        }

        String userid = claims.getId();
        if (type != null && type.equals("1")){
            /**
             *   关注
             */
            int num = friendService.focus(userid,friendid);
            if (num != 0){
                return new Result(true,StatusCode.OK,"关注成功");
            }else {
                return new Result(false,StatusCode.FAIL,"不能重复关注");
            }
        }else if (type.equals("0")){
            /**
             *  取消关注
             */
            int num = friendService.unFocus(userid,friendid);
            if (num != 0){
                return new Result(true,StatusCode.OK,"取消关注成功");
            }else {
                return new Result(false,StatusCode.FAIL,"没有关注该用户");
            }

        }

        return new Result(false, StatusCode.FAIL,"参数异常");
    }

}
