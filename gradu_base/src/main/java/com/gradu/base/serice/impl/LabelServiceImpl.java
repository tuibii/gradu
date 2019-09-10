package com.gradu.base.serice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.base.dao.LabelDao;
import com.gradu.base.entity.LabelEntity;
import com.gradu.base.serice.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService{

    @Autowired
    LabelDao labelDao;

    @Autowired
    IdWorker idWorker;

    public List<LabelEntity> getAllLabel(){
        return null;
    }

    public LabelEntity getLabelById(String id){
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addLabel(LabelEntity entity){

    }

    @Transactional(rollbackFor = Exception.class)
    public void updateLabel(LabelEntity dto){

    }

    @Transactional(rollbackFor = Exception.class)
    public void delLabelById(String id){

    }

    @Override
    public List<LabelEntity> findSearch(LabelEntity entity) {
        return null;
    }

    @Override
    public Page<LabelEntity> getLabelPage(int page, int size, LabelEntity dto) {
        return null;
    }

}
