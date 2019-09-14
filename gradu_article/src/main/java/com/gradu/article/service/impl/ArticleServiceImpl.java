package com.gradu.article.service.impl;

import com.gradu.article.dao.ArticleDao;
import com.gradu.article.entity.ArticleEntity;
import com.gradu.article.service.ArticleService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class ArticleServiceImpl extends MPBaseServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

}
