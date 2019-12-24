package com.lumengjun.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lumengjun.dao.ArticleMapper;
import com.lumengjun.dao.SlideMapper;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;
import com.lumengjun.entity.Comment;
import com.lumengjun.entity.Complain;
import com.lumengjun.entity.Slide;
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
	
	@Autowired
	SlideMapper slideMapper;
	
	
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


	@Override
	public PageInfo<Article> getHot(int page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		List<Article> list = ma.getHot();
		return new PageInfo<Article>(list);
	}


	@Override
	public List<Article> newList() {
		// TODO Auto-generated method stub
		return ma.newList(Cms.PAGE_KEY);
	}


	@Override
	public List<Slide> getSlides() {
		// TODO Auto-generated method stub
		return slideMapper.list();
	}


	@Override
	public PageInfo<Comment> getComments(int id, int page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		List<Comment> list = ma.getComments(id);
		return new PageInfo<Comment>(list);
	}


	@Override
	public int addComment(Comment comment) {
		
		ma.updateCommentCnt(comment.getArticleId());
		return ma.addComment(comment);
	}


	@Override
	public Article getArticlepage(int id, int articleid) {
		Article atricle=null;
		if(id>articleid){
			atricle = ma.getArticlepre(articleid);
		}else{
			atricle = ma.getArticlnext(articleid);
		}
		return atricle;
	}


	@Override
	public PageInfo<Article> getArticles(int channelId, int catId, int page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		List<Article> list =ma.getArticles(channelId,catId);
		return new PageInfo<Article>(list);
	}


	@Override
	public List<Category> getCategoriesByChannelId(int channelId) {
		
		return ma.getCategoriesByChannelId(channelId);
	}


	@Override
	public void addComplian(@Valid Complain complain) {
		
		// TODO Auto-generated method stub
		
				//添加投诉到数据库
				int result = ma.addCoplain(complain);
				// 增加投诉的数量
				if(result>0)
					ma.increaseComplainCnt(complain.getArticleId());
				
				
	}


	@Override
	public PageInfo<Complain> getPageInfoComplain(int page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		
		return new PageInfo<Complain>(ma.getComplain());
	}


	@Override
	public Complain getComplainId(int id) {
		// TODO Auto-generated method stub
		return ma.getComplainId(id);
	}

}
