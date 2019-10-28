package com.gradu.user.service.impl;

import com.gradu.user.dao.SysMenuDao;
import com.gradu.user.entity.SysMenuEntity;
import com.gradu.user.service.AdminService;
import com.gradu.user.service.SysMenuService;
import com.gradu.user.service.SysRoleMenuService;
import entity.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;
import util.MapUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl extends MPBaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Autowired
    AdminService adminService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for(SysMenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = adminService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单与角色关联
        sysRoleMenuService.removeByMap(new MapUtil().put("menu_id", menuId));
        //删除菜单
        this.removeById(menuId);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for(SysMenuEntity entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
