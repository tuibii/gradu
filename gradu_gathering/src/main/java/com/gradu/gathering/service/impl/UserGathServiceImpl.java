package com.gradu.gathering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.gathering.dao.UserGathDao;
import com.gradu.gathering.entity.UserGathEntity;
import com.gradu.gathering.service.UserGathService;
import org.springframework.stereotype.Service;
import service.impl.BaseServiceImpl;

import java.util.Map;

@Service
public class UserGathServiceImpl extends BaseServiceImpl<UserGathDao, UserGathEntity> implements UserGathService {


    @Override
    public QueryWrapper<UserGathEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<UserGathEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }
}
