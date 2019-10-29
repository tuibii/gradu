package com.gradu.user.controller;

import com.gradu.user.entity.SysMenuEntity;
import com.gradu.user.service.SysMenuService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
@CrossOrigin
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    HttpServletRequest request;
    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public Result nav(){
        Claims claims = (Claims) request.getAttribute("token");
        Long id = Long.valueOf(claims.getId());
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(id);
        return new Result(true, StatusCode.OK,"查询菜单成功",menuList);
    }
}
