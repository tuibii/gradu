package com.gradu.user.controller;

import com.gradu.user.entity.UserEntity;
import com.gradu.user.service.OssService;
import com.gradu.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String url = ossService.upload(file);
        return new Result(true, StatusCode.OK, "上传成功", url);
    }

    @PostMapping("avatar")
    public Result updateAvatar(@RequestParam("file") MultipartFile file) {
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims == null) {
            return new Result(false, StatusCode.FAIL, "未登录");
        }

        UserEntity entity = userService.getById(claims.getId());

        String url = ossService.upload(file);
        entity.setAvatar(url);
        userService.updateById(entity);

        return new Result(true, StatusCode.OK, "修改成功", url);
    }
}
