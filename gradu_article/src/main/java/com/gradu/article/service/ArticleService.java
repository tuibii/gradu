package com.gradu.article.service;

import com.gradu.article.entity.ArticleEntity;
import service.MPBaseService;

public interface ArticleService extends MPBaseService<ArticleEntity> {

    void add(ArticleEntity articleEntity);

    void delete(String id);

    void update(ArticleEntity articleEntity);

    ArticleEntity selectById(String id);

}
