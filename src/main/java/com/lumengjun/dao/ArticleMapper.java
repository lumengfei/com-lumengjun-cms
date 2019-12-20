package com.lumengjun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;
import com.lumengjun.entity.Comment;
import com.lumengjun.entity.Slide;

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

	
	
	List<Article> getHot();

	
	List<Article> newList(int pageKey);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT c.id,c.articleId,c.userId,u.username as userName,c.content,c.created FROM cms_comment as c "
			+ " LEFT JOIN cms_user as u ON u.id=c.userId "
			+ " WHERE articleId=#{value} ORDER BY c.created DESC")
	List<Comment> getComments(int id);

	/**
	 * 
	 * @param comment
	 * @return
	 */
	@Insert("INSERT INTO cms_comment(articleId,userId,content,created)"
			+ " VALUES(#{articleId},#{userId},#{content},NOW())")
	int addComment(Comment comment);

	/**
	 * 
	 * @param articleid
	 * @return
	 */
	Article getArticlepre(int articleid);

	/**
	 * 
	 * @param articleid
	 * @return
	 */
	Article getArticlnext(int articleid);

	/**
	 * 
	 * @param channelId
	 * @param catId
	 * @return
	 */
	List<Article> getArticles(@Param("channelId")int channelId,@Param("catId") int catId);

	/**
	 * 
	 * @param channelId
	 * @return
	 */
	@Select("SELECT id,name FROM cms_category where channel_id=#{value}")
	@ResultType(Category.class)
	List<Category> getCategoriesByChannelId(int channelId);

	
	@Update("UPDATE cms_article SET commentCnt=commentCnt+1 WHERE id=#{value}")
	void updateCommentCnt(int articleId);

	

}
