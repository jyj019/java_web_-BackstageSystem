package com.jyj019.service;

import java.util.List;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */

import com.jyj019.beans.User;

public interface UserService {

	public List<User> findAll();

	public User findOne(String username, String password);
	

}
