package com.gradu.treehole.service;

import com.gradu.treehole.entity.TreeholeEntity;

import java.util.List;

public interface TreeholeService {

    List<TreeholeEntity> findAll();

    TreeholeEntity selectById(String id);

    void save(TreeholeEntity entity);

    void update(TreeholeEntity entity);

    void deleteById(String id);

}
