package com.gradu.base.serice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.base.dao.LabelDao;
import com.gradu.base.entity.LabelEntity;
import com.gradu.base.serice.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;
import java.util.List;

@Service
public class LabelServiceImpl extends MPBaseServiceImpl<LabelDao,LabelEntity> implements LabelService{

    @Autowired
    IdWorker idWorker;

    public List<LabelEntity> getAllLabel(){
        return this.list();
    }

    public LabelEntity getLabelById(String id){
        return this.getLabelById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addLabel(LabelEntity entity){
        this.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateLabel(LabelEntity entity){
        this.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delLabelById(String id){
        this.removeById(id);
    }

    @Override
    public List<LabelEntity> findSearch(LabelEntity entity) {
        return null;
    }

    @Override
    public IPage<LabelEntity> getLabelPage(int page, int size, LabelEntity entity) {

        QueryWrapper<LabelEntity> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotEmpty(entity.getLabelname()),"labelname",entity.getLabelname());
        wrapper.eq(StringUtils.isNotEmpty(entity.getState()),"state",entity.getState());

        return this.page(new Page<>(page,size));
    }

}
