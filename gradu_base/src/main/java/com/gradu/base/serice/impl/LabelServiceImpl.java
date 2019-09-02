package com.gradu.base.serice.impl;

import com.gradu.base.dao.LabelDao;
import com.gradu.base.dto.LabelDTO;
import com.gradu.base.entity.LabelEntity;
import com.gradu.base.serice.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Override
    public List<LabelDTO> findSearch(LabelDTO dto) {

        List<LabelEntity> all = labelDao.findAll(new Specification<LabelEntity>() {
            @Override
            public Predicate toPredicate(Root<LabelEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                if (dto.getLabelname() != null  && !"".equals(dto.getLabelname())){
                    Predicate predicate = builder.like(root.get("labelname").as(String.class), "%" + dto.getLabelname() + "%");
                    predicates.add(predicate);
                }
                if (dto.getState() != null  && !"".equals(dto.getState())){
                    Predicate predicate = builder.equal(root.get("state").as(String.class),dto.getState());
                    predicates.add(predicate);
                }

                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        ArrayList<LabelDTO> labelEntities = new ArrayList<>();
        for (LabelEntity entity:all){
            labelEntities.add(transforDTO(entity));
        }

        return labelEntities;
    }

    @Override
    public Page<LabelDTO> getLabelPage(int page, int size, LabelDTO dto) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LabelEntity> labelEntityPage = labelDao.findAll(new Specification<LabelEntity>() {
            @Override
            public Predicate toPredicate(Root<LabelEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                if (dto.getLabelname() != null && !"".equals(dto.getLabelname())) {
                    Predicate predicate = builder.like(root.get("labelname").as(String.class), "%" + dto.getLabelname() + "%");
                    predicates.add(predicate);
                }
                if (dto.getState() != null && !"".equals(dto.getState())) {
                    Predicate predicate = builder.equal(root.get("state").as(String.class), dto.getState());
                    predicates.add(predicate);
                }

                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        ArrayList<LabelDTO> labelEntities = new ArrayList<>();
        for (LabelEntity labelEntity:labelEntityPage.getContent()){
            labelEntities.add(transforDTO(labelEntity));
        }
        return new PageImpl<LabelDTO>(labelEntities,pageable,labelEntityPage.getTotalElements());

    }


    private LabelDTO transforDTO(LabelEntity labelEntity){
        LabelDTO dto = new LabelDTO();
        BeanUtils.copyProperties(labelEntity,dto);
        return dto;
    }

}
