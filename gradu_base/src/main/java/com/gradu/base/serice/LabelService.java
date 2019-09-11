package com.gradu.base.serice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gradu.base.entity.LabelEntity;
import service.MPBaseService;

import java.util.List;

public interface LabelService extends MPBaseService<LabelEntity> {

    List<LabelEntity> getAllLabel();

    LabelEntity getLabelById(String id);

    void addLabel(LabelEntity entity);

    void updateLabel(LabelEntity entity);

    void delLabelById(String id);

    List<LabelEntity> findSearch(LabelEntity entity);

    IPage<LabelEntity> getLabelPage(int page, int size, LabelEntity entity);
}
