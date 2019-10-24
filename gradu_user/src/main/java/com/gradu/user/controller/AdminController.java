package com.gradu.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.user.dto.AdminDTO;
import com.gradu.user.entity.AdminEntity;
import com.gradu.user.service.AdminService;
import com.gradu.user.service.CaptchaService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletOutputStream;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CaptchaService captchaService;

    @PostMapping
    public Result add(@RequestBody AdminEntity adminEntity){
        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(adminEntity.getLoginname()!=null,"loginname",adminEntity.getLoginname());
        try {
            AdminEntity one = adminService.getOne(wrapper);
            if (one!=null){
                return  new Result(false,StatusCode.FAIL,"该账号已存在");
            }
        }catch (Exception e){
            return  new Result(false,StatusCode.FAIL,"无效的用户名");
        }

        adminService.add(adminEntity);

        return new Result(true, StatusCode.OK,"添加管理员成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody AdminDTO dto){

        //验证码是否正确
        boolean flag = captchaService.validate(dto.getUuid(), dto.getCaptcha());
        if(!flag){
            return new Result(false,StatusCode.FAIL,"验证码错误");
        }

        AdminEntity entity = adminService.login(dto);

        if (entity == null){
            return new Result(false,StatusCode.FAIL,"登陆失败");
        }

        String token = jwtUtil.cteateToken(entity.getId(), entity.getLoginname(), "admin");
        return new Result(true,StatusCode.OK,"登陆成功",token);
    }

    /**
     *  验证码
     * @param response
     * @param uuid
     * @throws IOException
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, String uuid)throws IOException {

        if (StringUtils.isNotEmpty(uuid)){
            //生成图片验证码
            BufferedImage image = captchaService.create(uuid);

            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.close();
        }
    }

}
