package com.lumengjun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lumengjun.dao.LockedMapper;
import com.lumengjun.entity.Article;
import com.lumengjun.service.LockedService;

@Service
public class LockedServiceImpl implements LockedService {

	@Autowired
	LockedMapper lockedMapper;

	@Override
	public PageInfo<Article> getArticle(Integer status, Integer page) {
		PageHelper.startPage(page, 5);
		List<Article> list = lockedMapper.getArticle(status);
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
	
	
	
}
