package com.gradu.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.base.entity.LabelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface LabelDao extends BaseMapper<LabelEntity> {


}
