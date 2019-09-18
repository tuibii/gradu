package com.gradu.gathering.service;

import com.gradu.gathering.entity.GatheringEntity;
import service.MPBaseService;

public interface GatheringService extends MPBaseService<GatheringEntity> {

    void add(GatheringEntity entity);

    void update(GatheringEntity entity);

    void delete(String id);

    GatheringEntity selectById(String id);

}
