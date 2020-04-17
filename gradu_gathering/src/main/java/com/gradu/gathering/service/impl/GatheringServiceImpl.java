package com.gradu.gathering.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.gathering.dao.GatheringDao;
import com.gradu.gathering.dto.GatheringDTO;
import com.gradu.gathering.entity.GatheringEntity;
import com.gradu.gathering.entity.UserGathEntity;
import com.gradu.gathering.service.GatheringService;
import com.gradu.gathering.service.UserGathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.BaseServiceImpl;
import service.impl.MPBaseServiceImpl;
import util.ConvertUtil;
import util.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GatheringServiceImpl extends BaseServiceImpl<GatheringDao, GatheringEntity> implements GatheringService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    UserGathService userGathService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GatheringEntity entity) {
        entity.setId(String.valueOf(idWorker.nextId()));
        entity.setState("1");
        entity.setCreatetime(new Date());
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
    public List<GatheringDTO> dtoList(Map<String, Object> params) {
        String userId = (String) params.get("userId");
        List<GatheringEntity> list = list(params);
        List<GatheringDTO> dtoList = ConvertUtil.sourceToTarget(list, GatheringDTO.class);

        if (StringUtils.isNotEmpty(userId)) {
            for (GatheringDTO gatheringDTO : dtoList) {
                params.clear();
                params.put("userid", userId);
                params.put("gathid", gatheringDTO.getId());
                List<UserGathEntity> userGathEntityList = userGathService.list(params);
                if (CollectionUtil.isNotEmpty(userGathEntityList)) {
                    gatheringDTO.setJoin(true);
                } else {
                    gatheringDTO.setJoin(false);
                }
            }
        }

        return dtoList;
    }

    @Override
    public QueryWrapper<GatheringEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<GatheringEntity> wrapper = new QueryWrapper<>();

        String state = (String) params.get("state");
        String creator = (String) params.get("creator");

        wrapper.eq(StringUtils.isNotEmpty(creator), "creator", creator);
        wrapper.eq(StringUtils.isNotEmpty(state),"state", state);

        return wrapper;
    }
}
