package com.jyj019.dao;

import java.util.List;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */

import com.jyj019.beans.User;

public interface UserDao {

	public List<User> findAll();
	

}
