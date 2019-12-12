package com.lumengjun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lumengjun.dao.ArticleMapper;
import com.lumengjun.entity.Article;
import com.lumengjun.momme.Cms;
import com.lumengjun.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper ma;
	@Override
	public PageInfo<Article> listByUser(Integer id, int page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		List<Article> list =ma.listByUser(id);
		
		return new PageInfo<Article>(list);
	}

}
