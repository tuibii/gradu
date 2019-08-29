package com.gradu.base.serice.impl;

import com.gradu.base.dao.LabelDao;
import com.gradu.base.dto.LabelDTO;
import com.gradu.base.entity.LabelEntity;
import com.gradu.base.serice.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelDao labelDao;

    @Autowired
    IdWorker idWorker;

    public List<LabelDTO> getAllLabel(){
        List<LabelEntity> labelEntityList = labelDao.findAll();

        List<LabelDTO> labelDTOList = new ArrayList<>();

        for (LabelEntity entity:labelEntityList){
            labelDTOList.add(transforDTO(entity));
        }

        return labelDTOList;
    }

    public LabelDTO getLabelById(String id){
        LabelEntity entity = labelDao.findById(id).get();
        return transforDTO(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addLabel(LabelDTO dto){
        LabelEntity labelEntity = new LabelEntity();
        BeanUtils.copyProperties(dto,labelEntity);
        labelEntity.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(labelEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateLabel(LabelDTO dto){
        LabelEntity labelEntity = new LabelEntity();
        BeanUtils.copyProperties(dto,labelEntity);

        labelDao.save(labelEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delLabelById(String id){
        labelDao.deleteById(id);
    }


    private LabelDTO transforDTO(LabelEntity labelEntity){
        LabelDTO dto = new LabelDTO();
        BeanUtils.copyProperties(labelEntity,dto);
        return dto;
    }

}
