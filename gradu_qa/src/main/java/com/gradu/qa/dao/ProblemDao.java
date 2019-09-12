package com.gradu.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.qa.entity.ProblemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProblemDao extends BaseMapper<ProblemEntity> {
}
