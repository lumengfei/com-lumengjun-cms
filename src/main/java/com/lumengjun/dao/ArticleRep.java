package com.lumengjun.dao;

import java.util.List;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.lumengjun.entity.Article;
//ElasticsearchTemplate
public interface ArticleRep extends ElasticsearchRepository<Article, Integer>{

	List<Article> findByTitle(String string);

}
