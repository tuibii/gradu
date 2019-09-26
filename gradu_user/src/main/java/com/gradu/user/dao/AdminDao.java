package com.gradu.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.user.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao extends BaseMapper<AdminEntity> {
}
