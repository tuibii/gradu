package com.gradu.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gradu.article.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleDao extends BaseMapper<ArticleEntity> {

}
