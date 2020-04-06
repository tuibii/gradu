package com.gradu.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.user.entity.DynamicEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DynamicDao extends BaseMapper<DynamicEntity> {
}
