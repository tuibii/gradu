package com.gradu.es.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.es.entity.ArticleEntity;

public interface ArticleService {

    void save(ArticleEntity articleEntity);

    Page<ArticleEntity> selectById(String key, int page, int size);
}
