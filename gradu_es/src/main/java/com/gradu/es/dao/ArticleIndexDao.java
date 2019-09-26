package com.gradu.es.dao;


import com.gradu.es.entity.ArticleIndexEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ArticleIndexDao extends ElasticsearchRepository<ArticleIndexEntity,String> {

    Page<ArticleIndexEntity> findByContentAndTitleLike(String title, String content, Pageable pageable);

}
