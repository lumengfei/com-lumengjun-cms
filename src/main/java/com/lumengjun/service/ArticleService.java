package com.lumengjun.service;

import com.github.pagehelper.PageInfo;
import com.lumengjun.entity.Article;

public interface ArticleService {

	/**
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	PageInfo<Article> listByUser(Integer id, int page);

}
