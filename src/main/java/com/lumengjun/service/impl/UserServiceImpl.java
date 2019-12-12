package com.lumengjun.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumengjun.common.CmsUtils;
import com.lumengjun.dao.UserMapper;
import com.lumengjun.entity.User;
import com.lumengjun.service.UserService;
/**
 * 
 * @author ASUS
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper ma;
	
	/**
	 * 
	 */
	@Override
	public User getUserName(String username) {
		// TODO Auto-generated method stub
		return ma.getUserName(username);
	}

	/**
	 * 
	 */
	@Override
	public int registerUser(User user) {
		user.setPassword(CmsUtils.encry(user.getPassword(), user.getUsername()));
		return ma.registerUser(user);
	}

	@Override
	public User getUser(User user) {
		user.setPassword(CmsUtils.encry(user.getPassword(), user.getUsername()));
		return ma.setPassword(user);
	}

}
