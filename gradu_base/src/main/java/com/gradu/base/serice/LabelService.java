package com.gradu.base.serice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.base.entity.LabelEntity;
import java.util.List;

public interface LabelService {

    List<LabelEntity> getAllLabel();

    LabelEntity getLabelById(String id);

    void addLabel(LabelEntity entity);

    void updateLabel(LabelEntity entity);

    void delLabelById(String id);

    List<LabelEntity> findSearch(LabelEntity entity);

    Page<LabelEntity> getLabelPage(int page, int size, LabelEntity entity);
}
