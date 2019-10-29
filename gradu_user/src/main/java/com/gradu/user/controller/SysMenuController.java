package com.gradu.user.controller;

import com.gradu.user.entity.SysMenuEntity;
import com.gradu.user.service.SysMenuService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    public List<SysMenuEntity> list(){
        List<SysMenuEntity> menuList = sysMenuService.list();
        for(SysMenuEntity sysMenuEntity : menuList){
            SysMenuEntity parentMenuEntity = sysMenuService.getById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

    @DeleteMapping("/delete/{menuId}")
    public Result delete(@PathVariable("menuId") long menuId){
        if(menuId <= 31){
            return new Result(false,StatusCode.FAIL,"系统菜单，无法删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return new Result(false,StatusCode.FAIL,"请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return new Result(true,StatusCode.OK,"删除成功");
    }


    @PostMapping("/update")
    public Result update(@RequestBody SysMenuEntity menu){

        sysMenuService.updateById(menu);

        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    public Result select(){
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return new Result(true,StatusCode.OK,"success",menuList);
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysMenuEntity menu){

        sysMenuService.save(menu);

        return new Result(true,StatusCode.OK,"保存成功");
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{menuId}")
    public Result info(@PathVariable("menuId") Long menuId){
        SysMenuEntity menu = sysMenuService.getById(menuId);
        return new Result(true,StatusCode.OK,"success",menu);
    }
}
