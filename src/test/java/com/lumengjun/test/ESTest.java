package com.lumengjun.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lumengjun.dao.ArticleRep;
import com.lumengjun.entity.Article;
import com.lumengjun.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-beans.xml"})
public class ESTest {

	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleRep articleRep;
	
	@Test
	public void add(){
		List<Article> list_Article = articleService.list_Article();
		//System.out.println(list_Article.size());
		articleService.add_Article(list_Article);
	}
	
	@Test
	public void sss(){
		Article article = new Article();
		article.setId(1);
		article.setTitle("卢梦均");
		article.setContent("暗室逢灯大纲");
		articleRep.save(article);
	}
	
	@Test
	public void select(){
		List<Article> findByTitle = articleRep.findByTitle("AAAAA");
		for (Article article : findByTitle) {
			System.out.println(article);
		}
	}
	
	@Test
	public void del(){
		
	}
	
	
}
