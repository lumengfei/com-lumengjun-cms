package com.lumengjun.controller;

import javax.servlet.http.HttpServletRequest;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;






import com.github.pagehelper.PageInfo;
import com.lumengjun.entity.Article;
import com.lumengjun.entity.User;
import com.lumengjun.momme.Cms;
import com.lumengjun.service.ArticleService;

/*
 * 
 */
@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/articles")
public String articles(HttpServletRequest request,@RequestParam(defaultValue="1") int page) {
		
		User loginUser = (User)request.getSession().getAttribute(Cms.USER);
		
		PageInfo<Article> articlePage = articleService.listByUser(loginUser.getId(),page);
		
		request.setAttribute("articlePage", articlePage);
		
		return "user/article/list";
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/comments")
	public String comments() {
		return "/user/comments/list";
	}
	
	/**
	 * 个人设置
	 * @return
	 */
	@RequestMapping("/personal")
	public String personal() {
		return "/user/personal/list";
	}
	
}
