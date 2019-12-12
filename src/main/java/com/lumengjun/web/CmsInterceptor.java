package com.lumengjun.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.lumengjun.entity.User;
import com.lumengjun.momme.Cms;



public class CmsInterceptor implements HandlerInterceptor{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// TODO Auto-generated method stub
		User loginUser = (User)request.getSession().getAttribute(Cms.USER);
		if(loginUser == null)
		{
			//request.s
			response.sendRedirect("/user/login");
			//request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		
		//放行 
		return true;
		
		
	}
	
}
