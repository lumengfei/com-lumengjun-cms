package com.lumengjun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.lumengjun.common.HLUtils;
import com.lumengjun.dao.ArticleRep;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.Category;
import com.lumengjun.entity.Channel;
import com.lumengjun.entity.Slide;
import com.lumengjun.service.ArticleService;

/**
 * 
 * @author ASUS
 *
 */
@Controller
public class IndexController {

	@Autowired
	ArticleService  articleService;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	ArticleRep articleRep;
	
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	@PostMapping("index")
	public String index(String key,HttpServletRequest request,@RequestParam(defaultValue="1")Integer page){
		System.err.println(key);
		request.getSession().setAttribute("key", key);
		Thread t1 = new Thread(){
			@Override
			public void run() {
				// 栏目
				List<Channel> channels = articleService.channelList();
				request.setAttribute("channels", channels);
			}
		};
		
		/*Thread t2 = new Thread(){
			@Override
			public void run() {
				//热门文章
				PageInfo<Article> articlepage = articleService.getHot(page);
				request.setAttribute("articlePage", articlepage);
		//List<Article> articlePage = articleRep.findByTitle(key);
		
				//	高量文章
		
			}
		};*/
		
		PageInfo<Article> articlepage = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, page, 3, new String[]{"title"}, "id", key);
		System.err.println(articlepage.getList().size());
		for (Article article : articlepage.getList()) {
			System.err.println(article);
		}
		request.setAttribute("articlePage", articlepage);
		Thread t3 = new Thread(){
			@Override
			public void run() {
		// 最新文章
		List<Article> newarticles =articleService.newList();
		request.setAttribute("newarticles", newarticles);
			}
		};
		
		Thread t4 = new Thread(){
			@Override
			public void run() {
		List<Slide>  slides=articleService.getSlides();
		request.setAttribute("slides", slides);
			}
		};
		t1.start();
		
		//t2.start();
		
		t3.start();
		
		t4.start();
		
		
		
		//PageInfo<Article> articlePage = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, page, 2, new String[]{"Title"}, "id", key);
		//request.setAttribute("articlePage", articlePage);
		return "index";
	}
	
	
	@RequestMapping(value={"index","/"})
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="1")int page) throws InterruptedException{
		
		Thread t1 = new Thread(){
			@Override
			public void run() {
				// 栏目
				List<Channel> channels = articleService.channelList();
				request.setAttribute("channels", channels);
			}
		};
		
		Thread t2 = new Thread(){
			@Override
			public void run() {
		//热门文章
		PageInfo<Article> articlepage = articleService.getHot(page);
		request.setAttribute("articlePage", articlepage);
			}
		};
		Thread t3 = new Thread(){
			@Override
			public void run() {
		// 最新文章
		List<Article> newarticles =articleService.newList();
		request.setAttribute("newarticles", newarticles);
			}
		};
		
		Thread t4 = new Thread(){
			@Override
			public void run() {
		List<Slide>  slides=articleService.getSlides();
		request.setAttribute("slides", slides);
			}
		};
		t1.start();
		
		t2.start();
		
		t3.start();
		
		t4.start();
		
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		
		return "index";
		
	}
	
	/**
	 * 
	 * @param request  请求
	 * @param channleId  栏目的id
	 * @param catId 分类的id
	 * @param page 页码
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("channel")
	public String channel(HttpServletRequest request,
			int channelId,
			@RequestParam(defaultValue="0") int catId,
			@RequestParam(defaultValue="1")  int page) throws InterruptedException {
		
		Thread  t1 =  new Thread() {
			public void run() {
		// 获取所有的栏目
		List<Channel> channels = articleService.channelList();
		request.setAttribute("channels", channels);
			};
		};
		
		Thread  t2 =  new Thread() {
			public void run() {
		// 当前栏目下  当前分类下的文章
		PageInfo<Article> articlePage= articleService.getArticles(channelId,catId, page);
		request.setAttribute("articlePage", articlePage);
			};
		};
		
		Thread  t3 =  new Thread() {
			public void run() {
		// 获取最新文章
		List<Article> lastArticles = articleService.newList();
		request.setAttribute("lastArticles", lastArticles);
			};
		};
		
		Thread  t4 =  new Thread() {
			public void run() {
		// 轮播图
		List<Slide> slides = articleService.getSlides();
		request.setAttribute("slides", slides);
		
			};
		};
		
		// 获取当前栏目下的所有的分类 catId
		Thread  t5 =  new Thread() {
			public void run() {
		// 
		List<Category> categoris= articleService.getCategoriesByChannelId(channelId);
		request.setAttribute("categoris", categoris);
		System.err.println("categoris is " + categoris);
			};
		};
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		
		// 参数回传
		request.setAttribute("catId", catId);
		request.setAttribute("channelId", channelId);
		
		return "channel";
	}
	
}
