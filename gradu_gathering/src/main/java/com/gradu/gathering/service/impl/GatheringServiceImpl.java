package com.gradu.gathering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.gathering.dao.GatheringDao;
import com.gradu.gathering.entity.GatheringEntity;
import com.gradu.gathering.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.BaseServiceImpl;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;

import java.util.Map;

@Service
public class GatheringServiceImpl extends BaseServiceImpl<GatheringDao, GatheringEntity> implements GatheringService {

    @Autowired
    IdWorker idWorker;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GatheringEntity entity) {
        entity.setId(String.valueOf(idWorker.nextId()));
        this.baseDao.insert(entity);
    }

    @CacheEvict(cacheNames = "gathering",key = "#entity.id")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GatheringEntity entity) {
        this.baseDao.updateById(entity);
    }

    @CacheEvict(cacheNames = "gathering",key = "#id")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        this.baseDao.deleteById(id);
    }

    @Cacheable(cacheNames = "gathering",key = "#id")
    @Override
    public GatheringEntity selectById(String id) {
        return this.baseDao.selectById(id);
    }

    @Override
    public QueryWrapper<GatheringEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<GatheringEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(params.get("state")!=null,"state",params.get("state"));
        return wrapper;
    }
}
