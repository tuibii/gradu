package com.gradu.friend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.friend.entity.FriendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FriendDao extends BaseMapper<FriendEntity> {
}
