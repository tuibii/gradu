package com.gradu.friend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.friend.entity.BlackListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlackListDao extends BaseMapper<BlackListEntity> {
}
