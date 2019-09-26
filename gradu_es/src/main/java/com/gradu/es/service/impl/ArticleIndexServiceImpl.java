package com.gradu.es.service.impl;

import com.gradu.es.dao.ArticleIndexDao;
import com.gradu.es.entity.ArticleIndexEntity;
import com.gradu.es.service.ArticleIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleIndexServiceImpl implements ArticleIndexService {

    @Autowired
    ArticleIndexDao articleIndexDao;

    @Override
    public void save(ArticleIndexEntity articleIndexEntity) {
        articleIndexDao.save(articleIndexEntity);
    }

    @Override
    public Page<ArticleIndexEntity> selectByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return articleIndexDao.findByContentAndTitleLike(key,key,pageable);
    }


}
