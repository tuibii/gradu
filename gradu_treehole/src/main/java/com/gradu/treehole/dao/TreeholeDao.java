package com.gradu.treehole.dao;

import com.gradu.treehole.entity.TreeholeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TreeholeDao extends MongoRepository<TreeholeEntity,String> {

    List<TreeholeEntity> findTreeholeEntitiesByParentid(String parentid);

}
