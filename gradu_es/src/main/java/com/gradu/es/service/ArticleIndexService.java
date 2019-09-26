package com.gradu.es.service;

import com.gradu.es.entity.ArticleIndexEntity;
import org.springframework.data.domain.Page;

public interface ArticleIndexService {

    void save(ArticleIndexEntity articleIndexEntity);

    Page<ArticleIndexEntity> selectByKey(String key, int page, int size);
}
