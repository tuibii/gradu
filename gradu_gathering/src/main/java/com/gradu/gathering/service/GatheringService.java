package com.gradu.gathering.service;

import com.gradu.gathering.entity.GatheringEntity;
import service.BaseService;
import service.MPBaseService;

public interface GatheringService extends BaseService<GatheringEntity> {

    void add(GatheringEntity entity);

    void update(GatheringEntity entity);

    void delete(String id);

    GatheringEntity selectById(String id);

}
