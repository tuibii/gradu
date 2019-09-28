package com.gradu.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.user.entity.AdminEntity;
import com.gradu.user.service.AdminService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping
    public Result add(@RequestBody AdminEntity adminEntity){
        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(adminEntity.getLoginname()!=null,"loginname",adminEntity.getLoginname());
        AdminEntity one = adminService.getOne(wrapper);
        if (one!=null){
            return  new Result(false,StatusCode.FAIL,"该账号已存在");
        }

        adminService.add(adminEntity);

        return new Result(true, StatusCode.OK,"添加管理员成功");
    }

    @PostMapping("/login")
    public Result login(AdminEntity adminEntity){

        AdminEntity entity = adminService.login(adminEntity);

        if (entity == null){
            return new Result(false,StatusCode.FAIL,"登陆失败");
        }

        return new Result(true,StatusCode.OK,"登陆成功");
    }

}
