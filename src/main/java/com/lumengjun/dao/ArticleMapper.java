package com.lumengjun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;

public interface ArticleMapper {
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Article> listByUser(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Update("update cms_article set deleted=1 where id=#{value}")
	int deletearticle(Integer id);
	/**
	 * 
	 * @return
	 */
	@Select("SELECT id,`name` from cms_channel")
	List<Channel> channelList();

	/**
	 * 
	 * @param cid
	 * @return
	 */
	@Select("SELECT id,`name` from cms_category where channel_id=#{value}")
	List<Category> categoryList(Integer cid);

	/**
	 * 
	 * @param article
	 * @return
	 */
	@Insert("INSERT INTO cms_article(title,content,picture,channel_id,category_id,user_id,hits,hot,status,deleted,created,updated,commentCnt,articleType)"
			+ " VALUES(#{title},#{content},#{picture},#{channelId},#{categoryId},#{userId},0,0,0,0,now(),now(),0,#{articleType})")
	int add(Article article);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Article getArticleId(Integer id);

	@Update("UPDATE cms_article SET title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
			+ " category_id=#{categoryId},status=0,"
			+ "updated=now() WHERE id=#{id} ")
	int updateArticle(Article article);

}
