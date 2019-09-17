package com.gradu.qa.service.impl;

import com.gradu.qa.dao.ReplyDao;
import com.gradu.qa.entity.ReplyEntity;
import com.gradu.qa.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;

@Service
public class ReplyServiceImpl extends MPBaseServiceImpl<ReplyDao, ReplyEntity> implements ReplyService {

    @Autowired
    IdWorker idWorker;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReplyEntity replyEntity) {
        replyEntity.setId(String.valueOf(idWorker));
        this.baseMapper.insert(replyEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "reply",key = "#id")
    @Override
    public void delete(String id) {
        this.baseMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "reply",key = "#replyEntity.id")
    @Override
    public void update(ReplyEntity replyEntity) {
        this.baseMapper.updateById(replyEntity);
    }

    @Cacheable(cacheNames = "reply",key = "#id")
    @Override
    public ReplyEntity selectById(String id) {
        return this.baseMapper.selectById(id);
    }
}
