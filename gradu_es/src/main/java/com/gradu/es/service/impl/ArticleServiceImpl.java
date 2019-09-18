package com.gradu.es.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.es.dao.ArticleDao;
import com.gradu.es.entity.ArticleEntity;
import com.gradu.es.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public void save(ArticleEntity articleEntity) {
        articleDao.save(articleEntity);
    }

    @Override
    public Page<ArticleEntity> selectById(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return articleDao.findArticleEntitiesByTitleOrContentLike(key,key,pageable);
    }


}
