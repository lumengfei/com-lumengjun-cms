package com.lumengjun.service;

import java.util.List;





import com.github.pagehelper.PageInfo;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;

public interface ArticleService {

	/**
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	PageInfo<Article> listByUser(Integer id, int page);

	/**
	 * 
	 * @param id
	 * @return
	 */
	int deletearticle(Integer id);

	/**
	 * 
	 * @return
	 */
	List<Channel> channelList();

	/**
	 * 
	 * @param cid
	 * @return
	 */
	List<Category> categoryList(Integer cid);

	/**
	 * 
	 * @param article
	 * @return
	 */
	int add(Article article);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Article getArticleId(Integer id);

	/**
	 * 
	 * @param article
	 * @return
	 */
	int updateArticle(Article article);

}
