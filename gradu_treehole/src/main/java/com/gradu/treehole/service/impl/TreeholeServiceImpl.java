package com.gradu.treehole.service.impl;

import com.gradu.treehole.dao.TreeholeDao;
import com.gradu.treehole.entity.TreeholeEntity;
import com.gradu.treehole.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

@Service
public class TreeholeServiceImpl implements TreeholeService {

    @Autowired
    TreeholeDao treeholeDao;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<TreeholeEntity> findAll() {
        return treeholeDao.findAll();
    }

    @Override
    public TreeholeEntity selectById(String id) {
        return treeholeDao.findById(id).get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TreeholeEntity entity) {
        entity.set_id(String.valueOf(idWorker.nextId()));
        treeholeDao.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(TreeholeEntity entity) {
        treeholeDao.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        treeholeDao.deleteById(id);
    }
}
