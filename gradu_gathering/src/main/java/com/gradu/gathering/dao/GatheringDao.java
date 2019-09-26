package com.gradu.gathering.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.gathering.entity.GatheringEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface GatheringDao extends BaseMapper<GatheringEntity> {
}
