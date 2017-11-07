package com.jyj019.utils;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 封装工具类
 * 1.加载驱动
 * 2.获取链接
 * 3.关闭资源
 * 4.封装增删改的方法
 * @author jyj019
 *
 */

public class DbUtils_c3p0 {
	private static ComboPooledDataSource cpd= null;
	static {
		// 初始化链接池
		cpd = new ComboPooledDataSource("mysql");
	}
	
	//  获取链接的方法
	public static Connection getConnection() throws SQLException {
		if(cpd!=null) {
			return cpd.getConnection();
		}
		return null;
	}
	// 增删改方法 
	public static int excuteUpdate(String sql,Object...params) {
		Connection connection=null;
		PreparedStatement pStatement = null;
		try {
			// 建立连接
			connection=getConnection();
			// 创建预编译命令
			pStatement = connection.prepareStatement(sql);
			// 赋值
			for (int i = 0; i < params.length; i++) {
				// 从可变参数里面拿值
				pStatement.setObject(i+1, params[i]);
			}
			// 执行sql语句 返回结果集
			int result = pStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 异常的处理    封装工具类的时候里面不要有任何的打印输出
			throw new RuntimeException(e);
		}finally {
			close(null, pStatement, connection);
		}

	}
	// 关闭资源 1.结果集 2.命令对象 3.链接
	public static void close(ResultSet resultSet,Statement statement,Connection connection) {
		if(resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
