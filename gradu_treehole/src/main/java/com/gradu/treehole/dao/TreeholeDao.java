package com.gradu.treehole.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.treehole.entity.TreeholeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreeholeDao extends MongoRepository<TreeholeEntity,String> {

    Page<TreeholeEntity> findTreeholeEntitiesByParentid(String parentid, Pageable pageable);

}
