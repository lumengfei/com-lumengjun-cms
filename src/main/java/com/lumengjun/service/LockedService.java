package com.lumengjun.service;




import com.github.pagehelper.PageInfo;
import com.lumengjun.entity.Article;



public interface LockedService {

	PageInfo<Article> getArticle(Integer status, Integer page);

	Article getInfoById(Integer id);

	int setCheckStatus(Integer id, Integer status);

	int setArticeHot(Integer id, Integer status);

}
