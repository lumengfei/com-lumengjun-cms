package com.lumengjun.dao;

import java.util.List;

import com.lumengjun.entity.Article;

public interface ArticleMapper {

	List<Article> listByUser(Integer id);

}
