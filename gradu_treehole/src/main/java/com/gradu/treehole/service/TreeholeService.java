package com.gradu.treehole.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.treehole.entity.TreeholeEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreeholeService {

    List<TreeholeEntity> findAll();

    TreeholeEntity selectById(String id);

    void add(TreeholeEntity entity);

    void update(TreeholeEntity entity);

    void deleteById(String id);

    Page<TreeholeEntity> findTreeholeEntityByParentid(String parentid,int page,int size);

    void thumbup(String userid,String treeholeid);

    void unthumbup(String userid,String treeholeid);
}
