package com.lumengjun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lumengjun.dao.ArticleMapper;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;
import com.lumengjun.momme.Cms;
import com.lumengjun.service.ArticleService;
/**
 * 
 * @author ASUS
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper ma;
	
	
	@Override
	public PageInfo<Article> listByUser(Integer id, int page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		List<Article> list =ma.listByUser(id);
		
		return new PageInfo<Article>(list);
	}


	@Override
	public int deletearticle(Integer id) {
		// TODO Auto-generated method stub
		return ma.deletearticle(id);
	}


	@Override
	public List<Channel> channelList() {
		
		return ma.channelList();
	}


	@Override
	public List<Category> categoryList(Integer cid) {
		
		return ma.categoryList(cid);
	}


	@Override
	public int add(Article article) {
		
		return ma.add(article);
	}


	@Override
	public Article getArticleId(Integer id) {
		
		return ma.getArticleId(id);
	}


	@Override
	public int updateArticle(Article article) {
		// TODO Auto-generated method stub
		return ma.updateArticle(article);
	}

}
