package com.gradu.qa.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.qa.dao.ProblemDao;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;

import java.util.List;

@Service
public class ProblemServiceImpl extends MPBaseServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Autowired
    IdWorker idWorker;

    @Override
    public Page<ProblemEntity> getNewProblem(int current, int size,String labelid) {
        List<ProblemEntity> newProblemList = this.baseMapper.newProblemList(labelid);
        Page<ProblemEntity> page = new Page<>(current,size,newProblemList.size());
        page.setRecords(newProblemList);
        return page;
    }

    @Override
    public Page<ProblemEntity> getHotProblem(int current, int size,String labelid) {
        List<ProblemEntity> hotProblemList = this.baseMapper.hotProblemList(labelid);
        Page<ProblemEntity> page = new Page<>(current,size,hotProblemList.size());
        page.setRecords(hotProblemList);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ProblemEntity problemEntity) {
        problemEntity.setId(String.valueOf(idWorker.nextId()));
        this.baseMapper.insert(problemEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "problem",key = "#id")
    @Override
    public void delete(String id) {
        this.baseMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "problem",key = "#problemEntity.id")
    @Override
    public void update(ProblemEntity problemEntity) {
        this.baseMapper.updateById(problemEntity);
    }

    @Cacheable(cacheNames = "problem",key = "#id")
    @Override
    public ProblemEntity selectById(String id) {
        return this.baseMapper.selectById(id);
    }


}
