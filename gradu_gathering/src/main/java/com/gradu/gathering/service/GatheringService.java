package com.gradu.gathering.service;

import com.gradu.gathering.dto.GatheringDTO;
import com.gradu.gathering.entity.GatheringEntity;
import service.BaseService;
import service.MPBaseService;

import java.util.List;
import java.util.Map;

public interface GatheringService extends BaseService<GatheringEntity> {

    void add(GatheringEntity entity);

    void update(GatheringEntity entity);

    void delete(String id);

    GatheringEntity selectById(String id);

    List<GatheringDTO> dtoList(Map<String, Object> params);

}
