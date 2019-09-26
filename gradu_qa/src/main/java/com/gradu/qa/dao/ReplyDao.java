package com.gradu.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.qa.entity.ReplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ReplyDao extends BaseMapper<ReplyEntity> {
}
