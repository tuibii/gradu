package com.gradu.treehole.dao;

import com.gradu.treehole.entity.TreeholeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreeholeDao extends MongoRepository<TreeholeEntity,String> {

}
