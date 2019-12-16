package com.gradu.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.qa.dao.ProblemDao;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.BaseServiceImpl;
import util.IdWorker;

import java.io.Serializable;
import java.util.Map;

@Service
public class ProblemServiceImpl extends BaseServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Autowired
    IdWorker idWorker;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ProblemEntity problemEntity) {
        problemEntity.setId(String.valueOf(idWorker.nextId()));
        this.baseDao.insert(problemEntity);
    }


    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "problem",key = "#id")
    @Override
    public void delete(String id) {
        this.baseDao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "problem",key = "#problemEntity.id")
    @Override
    public void update(ProblemEntity problemEntity) {
        this.baseDao.updateById(problemEntity);
    }

    @Cacheable(cacheNames = "problem",key = "#id")
    @Override
    public ProblemEntity selectById(String id) {
        return this.baseDao.selectById(id);
    }


    @Override
    public QueryWrapper<ProblemEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper wrapper = new QueryWrapper();

        return wrapper;
    }
}
