package com.jyj019.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import com.jyj019.beans.User;
import com.jyj019.utils.DbUtils_c3p0;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */


public class UserDaoImpl {

	public int insert(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user(uid,username,password,name,sex,identity,school,telephone,email,state,code) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params= {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getSex(),user.getIdentity(),user.getSchool(),user.getTelephone(),user.getEmail(),user.getState(),user.getCode()};
		return DbUtils_c3p0.excuteUpdate(sql, params);
	}

	public User findByCode(String code) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		try {
			connection=DbUtils_c3p0.getConnection();
			pStatement=connection.prepareStatement("select * from user where code=?");
			pStatement.setString(1,code );
			resultSet = pStatement.executeQuery();
			User user=null;
			if (resultSet.next()) {
				String uid = resultSet.getString("uid");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String identity = resultSet.getString("identity");
				String school = resultSet.getString("school");
				String telephone=resultSet.getString("telephone");
				String email=resultSet.getString("email");
				int state = resultSet.getInt("state");
				user=new User(uid, username, password, name, sex, identity, school, telephone, email, state, code, null,null);
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			DbUtils_c3p0.close(resultSet, pStatement, connection);
		}
		
	}

	public int update(User user) {
		// TODO Auto-generated method stub
		String sql="update user set username=?,password=?,name=?,sex=?,identity=?,school=?,telephone=?,email=?,state=?,code=? where uid=?";
		Object[] params= {user.getUsername(),user.getPassword(),user.getName(),user.getSex(),user.getIdentity(),user.getSchool(),user.getTelephone(),user.getEmail(),user.getState(),user.getCode(),user.getUid()};
		return DbUtils_c3p0.excuteUpdate(sql, params);
	}
	
	public int delById(String uid) {
		String sql = "delete from user where uid=?";
		Object[] params = {uid};
		return DbUtils_c3p0.excuteUpdate(sql, params);
	}
	public List<User> findAll(){
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		List<User> list1=new ArrayList<>();
		try {
			connection=DbUtils_c3p0.getConnection();
			pStatement=connection.prepareStatement("select * from user");
			resultSet=pStatement.executeQuery();
			while(resultSet.next()) {
				String uid = resultSet.getString("uid");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String identity = resultSet.getString("identity");
				String school = resultSet.getString("school");
				String telephone=resultSet.getString("telephone");
				String email=resultSet.getString("email");
				int state = resultSet.getInt("state");
				String code=resultSet.getString("code");
				User user=new User(uid, username, password, name, sex, identity, school, telephone, email, state, code, null, null);
				list1.add(user);
				
			}
			return list1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally {
			DbUtils_c3p0.close(resultSet, pStatement, connection);
		}
	}
	
	public User findOne(String username,String password) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		try {
			connection=DbUtils_c3p0.getConnection();
			pStatement=connection.prepareStatement("select * from user where username=? and password=?");
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			resultSet=pStatement.executeQuery();
			User user=null;
			while(resultSet.next()) {
				String uid = resultSet.getString("uid");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String identity = resultSet.getString("identity");
				String school = resultSet.getString("school");
				String telephone=resultSet.getString("telephone");
				String email=resultSet.getString("email");
				int state = resultSet.getInt("state");
				String code=resultSet.getString("code");
				user=new User(uid, username, password, name, sex, identity, school, telephone, email, state, code, null, null);
				
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException();
		}finally {
			DbUtils_c3p0.close(resultSet, pStatement, connection);
		}
		
	}
	
	public User findOne1(String uid) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		try {
			connection=DbUtils_c3p0.getConnection();
			pStatement=connection.prepareStatement("select * from user where uid=?");
			pStatement.setString(1, uid);
			
			resultSet=pStatement.executeQuery();
			User user=null;
			while(resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String identity = resultSet.getString("identity");
				String school = resultSet.getString("school");
				String telephone=resultSet.getString("telephone");
				String email=resultSet.getString("email");
				int state = resultSet.getInt("state");
				String code=resultSet.getString("code");
				user=new User(uid, username, password, name, sex, identity, school, telephone, email, state, code, null, null);
				
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException();
		}finally {
			DbUtils_c3p0.close(resultSet, pStatement, connection);
		}
		
	}
	
	public int findCount() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DbUtils_c3p0.getConnection();
			pStatement = connection.prepareStatement("select count(*) as count from user");
			resultSet = pStatement.executeQuery();
			int count = 0;
			if(resultSet.next()) {
				 count = resultSet.getInt("count");
			}
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally {
			DbUtils_c3p0.close(resultSet, pStatement, connection);
		}	
	}

	public List<User> findPage(int currentPage, int pageSize) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<User> list = new ArrayList<>();
		try {
			connection = DbUtils_c3p0.getConnection();
			pStatement = connection.prepareStatement("select * from user limit ?,?");
			pStatement.setInt(1, (currentPage-1)*pageSize);
			pStatement.setInt(2, pageSize);
			resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String uid = resultSet.getString("uid");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String identity = resultSet.getString("identity");
				String school = resultSet.getString("school");
				String telephone=resultSet.getString("telephone");
				String email=resultSet.getString("email");
				int state = resultSet.getInt("state");
				String code=resultSet.getString("code");
				list.add(new User(uid, username, password, name, sex, identity, school, telephone, email, state, code, null, null));
			}
		return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally {
			DbUtils_c3p0.close(resultSet, pStatement, connection);
		}	
	}
	
}
