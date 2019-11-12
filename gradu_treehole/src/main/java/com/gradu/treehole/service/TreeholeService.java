package com.gradu.treehole.service;

import com.gradu.treehole.entity.TreeholeEntity;

import java.util.List;

public interface TreeholeService {

    List<TreeholeEntity> list();

    TreeholeEntity selectById(String id);

    void add(TreeholeEntity entity);

    void update(TreeholeEntity entity);

    void deleteById(String id);

    List<TreeholeEntity> findTreeholeEntityByParentid(String parentid);

    void thumbup(String userid,String treeholeid);

    void unthumbup(String userid,String treeholeid);
}
