package com.jyj019.service.impl;


import java.util.List;

import com.jyj019.beans.PageBean;
import com.jyj019.beans.User;
import com.jyj019.dao.UserDao;
import com.jyj019.dao.impl.UserDaoImpl;
import com.jyj019.service.UserService;
import com.jyj019.utils.MailUtils;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */

public class UserServiceImpl implements UserService {
	
	public void regist(User user) {
		// TODO Auto-generated method stub
		new UserDaoImpl().insert(user);
		System.out.println("Serviceregist1");
		try {
			MailUtils.sendMail(user.getEmail(),"亲爱的"+user.getName()+"欢迎你来到前锋,点击下面按钮激活账号<br />"
					+"<a href='http://localhost/work1/servlet/UserServlet?method=active&code="+user.getCode()+"'>点击激活</a>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int active(String code) {
		// TODO Auto-generated method stub
		System.out.println("Servicecode1");
		User user = new UserDaoImpl().findByCode(code);
		int flag = 0;
		if(user!=null) {
			flag = 1;
			// 改变状态码
			user.setState(1);
			user.setCode(null);
			new UserDaoImpl().update(user);
		}else {
			flag = 0;
		}
		System.out.println("Serviceregist2");
		return flag;
	}

	@Override
	public List<User> findAll() {
		
		
		return new UserDaoImpl().findAll();
	}
	
	@Override
	public User findOne(String username,String password) {
		
		return new UserDaoImpl().findOne(username, password);
	}
	
	public int insert(User user) {
		// TODO Auto-generated method stub
		return new UserDaoImpl().insert(user);
	}
	
	public int delById(String uid) {
		// TODO Auto-generated method stub
		return new UserDaoImpl().delById(uid);
	}
	
	public int update(User user) {
		// TODO Auto-generated method stub
		return new UserDaoImpl().update(user);
	}
	
	public User findOne1(String uid) {
		
		return new UserDaoImpl().findOne1(uid);
	}
	
	public PageBean<User> findPage(int currentPage, int pageSize) {
		
		int totalCount = new UserDaoImpl().findCount();
		List<User> list = new UserDaoImpl().findPage(currentPage,pageSize);
		// select * from product limit (currentPage-1)*pageSzie,pageSize
		
		return new PageBean<User>(currentPage, pageSize, totalCount, list);
	}
	
}
