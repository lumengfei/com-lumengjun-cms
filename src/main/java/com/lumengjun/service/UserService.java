package com.lumengjun.service;

import javax.validation.Valid;

import com.lumengjun.entity.User;

public interface UserService {

	/**
	 * 
	 * @param username
	 * @return
	 */
	User getUserName(String username);

	/**
	 * 
	 * @param user
	 * @return
	 */
	int registerUser(User user);

	User getUser(@Valid User user);

	/**
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	User getToUser(String name, String pwd);

}
