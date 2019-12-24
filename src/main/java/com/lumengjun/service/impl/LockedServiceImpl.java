package com.lumengjun.service.impl;

import java.util.List;

import net.sf.jsqlparser.expression.operators.relational.LikeExpression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lumengjun.dao.LockedMapper;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.Link;
import com.lumengjun.momme.Cms;
import com.lumengjun.service.LockedService;

@Service
public class LockedServiceImpl implements LockedService {

	@Autowired
	LockedMapper lockedMapper;

	@Override
	public PageInfo<Article> getArticle(Integer status, Integer page) {
		PageHelper.startPage(page, Cms.PAGE_KEY);
		List<Article> list=null;
		if(status==-1){
		 list = lockedMapper.getArticleqc();
		}else{
		list = lockedMapper.getArticle(status);
		}
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		return pageInfo;
	}

	@Override
	public Article getInfoById(Integer id) {
		// TODO Auto-generated method stub
		return lockedMapper.getInfoById(id);
	}

	@Override
	public int setCheckStatus(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return lockedMapper.setCheckStatus(id,status);
	}

	@Override
	public int setArticeHot(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return lockedMapper.setArticeHot(id,status);
	}

	@Override
	public PageInfo<Link> getLinkList(int page) {
		PageHelper.startPage(page, 40);
		List<Link> list = lockedMapper.getLinkList();
		
		return new PageInfo<Link>(list);
	}

	
	
	
	
}
