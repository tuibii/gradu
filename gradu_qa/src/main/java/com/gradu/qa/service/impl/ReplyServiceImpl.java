package com.gradu.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.qa.dao.ReplyDao;
import com.gradu.qa.dto.ReplyDTO;
import com.gradu.qa.entity.ReplyEntity;
import com.gradu.qa.service.ReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl extends MPBaseServiceImpl<ReplyDao, ReplyEntity> implements ReplyService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReplyEntity replyEntity) {
        replyEntity.setId(String.valueOf(idWorker.nextId()));
        replyEntity.setCreatetime(new Date());
        replyEntity.setUpdatetime(new Date());
        replyEntity.setRate(0.0);
        replyEntity.setRateCount(0);
        this.baseMapper.insert(replyEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        this.baseMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReplyEntity replyEntity) {
        this.baseMapper.updateById(replyEntity);
    }

    @Override
    public ReplyEntity selectById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<ReplyDTO> listByProblemId(String problemId) {
        QueryWrapper<ReplyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("problemid",problemId);
        wrapper.orderByDesc("createtime");
        List<ReplyEntity> replyEntityList = baseMapper.selectList(wrapper);
        List<ReplyDTO> dtoList = new ArrayList<>();
        replyEntityList.forEach(item -> {
            ReplyDTO dto = entity2DTO(item);
            dtoList.add(dto);
        });
        return dtoList;
    }

    private ReplyDTO entity2DTO(ReplyEntity replyEntity) {
        ReplyDTO dto = new ReplyDTO();
        BeanUtils.copyProperties(replyEntity,dto);
        return dto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void rate(String userId, Double rate, ReplyEntity replyEntity) {
        double sumRate = replyEntity.getRate() * replyEntity.getRateCount();
        replyEntity.setRate((sumRate + rate) / (replyEntity.getRateCount() + 1));
        replyEntity.setRateCount(replyEntity.getRateCount() + 1);
        this.update(replyEntity);
        redisTemplate.opsForSet().add("problem:reply:rate:" + replyEntity.getId(), userId);
    }
}
