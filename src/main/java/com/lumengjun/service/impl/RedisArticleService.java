package com.lumengjun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lumengjun.entity.Article;
import com.lumengjun.service.RedisArticle;

@Service
public class RedisArticleService implements RedisArticle{

	@Autowired
	RedisTemplate<String, Article> redisTemplate;
	
	public void save(Article article){
		redisTemplate.opsForList().leftPush("redis_article", article);
	}
}
