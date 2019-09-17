package com.gradu.article.service.impl;

import com.gradu.article.dao.ArticleDao;
import com.gradu.article.entity.ArticleEntity;
import com.gradu.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;

@Service
public class ArticleServiceImpl extends MPBaseServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Autowired
    IdWorker idWorker;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ArticleEntity articleEntity) {
        articleEntity.setId(String.valueOf(idWorker.nextId()));
        this.baseMapper.insert(articleEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "article",key = "#id")
    @Override
    public void delete(String id) {
        this.baseMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "article",key = "#articleEntity.id")
    @Override
    public void update(ArticleEntity articleEntity) {
        this.baseMapper.updateById(articleEntity);
    }

    @Cacheable(cacheNames = "article",key = "#id")
    @Override
    public ArticleEntity selectById(String id) {
        return this.baseMapper.selectById(id);
    }
}
