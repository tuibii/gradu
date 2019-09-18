package com.gradu.es.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.es.entity.ArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ArticleDao extends ElasticsearchRepository<ArticleEntity,String> {

    Page<ArticleEntity> findArticleEntitiesByTitleOrContentLike(String title, String content, Pageable pageable);

}
