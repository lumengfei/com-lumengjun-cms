package com.lumengjun.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.aspectj.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lumengjun.cms.utils.FileUtilIO;
import com.lumengjun.entity.Article;
import com.lumengjun.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ArtTest {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ArticleService articleService;
	
	@Test
	public void artTest(){
		File f =new File("D:\\1708E3");
		String[] list = f.list();
		Article article = new Article();
		for (String string : list) {
			int lastIndexOf = string.lastIndexOf(".");
			String substring = string.substring(0, lastIndexOf);
			article.setTitle(substring);
			File ff =new File("D:\\1708E1"+"\\"+string);
			try {
				FileReader fr=new FileReader(ff);
				BufferedReader br =new BufferedReader(fr);
				String nn="";
				String nr="";
				while ((nn=br.readLine())!=null) {
					nr+=nn+"<br/>";
				}
				
				article.setContent(nr);
				article.setChannelId(1);
				article.setCategoryId(1);
				article.setUserId(69);
				article.setArticleType(0);
				article.setPicture("");
				//articleService.add(article);
				String jsonString = JSON.toJSONString(article);
				kafkaTemplate.send("lumengjun", "ArticleAdd", jsonString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void addMySQLArticle(){
		kafkaTemplate.send("lumengjun", "addMySQLArticle", "");
	}
	
}
