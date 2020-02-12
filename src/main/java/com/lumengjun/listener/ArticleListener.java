package com.lumengjun.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.lumengjun.dao.ArticleRep;
import com.lumengjun.entity.Article;
import com.lumengjun.service.ArticleService;

public class ArticleListener implements MessageListener<String, String>{

	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleRep articleRep;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String value = data.value();
		System.err.println("收到信息=============");
		if(value.startsWith("add")){
			
			String[] split = value.split("=");
			
			Article parseObject = JSON.parseObject(split[1], Article.class);
			
			System.err.println("es"+"添加方法");
			
			articleRep.save(parseObject);
			
		}else if(value.startsWith("del")){
			
			String[] split = value.split("=");
			
			System.err.println("es"+"删除方法");
			
			articleRep.deleteById(Integer.parseInt(split[1]));
		}
		//Article parseObject = JSON.parseObject(value, Article.class);
		//articleService.add(parseObject);
	}

}
