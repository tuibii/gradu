package com.gradu.base.dao;

import com.gradu.base.entity.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelDao extends JpaRepository<LabelEntity,String>, JpaSpecificationExecutor<LabelEntity> {



}
