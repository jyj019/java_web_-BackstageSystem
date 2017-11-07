package com.jyj019.utils;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * ��װ������
 * 1.��������
 * 2.��ȡ����
 * 3.�ر���Դ
 * 4.��װ��ɾ�ĵķ���
 * @author jyj019
 *
 */

public class DbUtils_c3p0 {
	private static ComboPooledDataSource cpd= null;
	static {
		// ��ʼ�����ӳ�
		cpd = new ComboPooledDataSource("mysql");
	}
	
	//  ��ȡ���ӵķ���
	public static Connection getConnection() throws SQLException {
		if(cpd!=null) {
			return cpd.getConnection();
		}
		return null;
	}
	// ��ɾ�ķ��� 
	public static int excuteUpdate(String sql,Object...params) {
		Connection connection=null;
		PreparedStatement pStatement = null;
		try {
			// ��������
			connection=getConnection();
			// ����Ԥ��������
			pStatement = connection.prepareStatement(sql);
			// ��ֵ
			for (int i = 0; i < params.length; i++) {
				// �ӿɱ����������ֵ
				pStatement.setObject(i+1, params[i]);
			}
			// ִ��sql��� ���ؽ����
			int result = pStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// �쳣�Ĵ���    ��װ�������ʱ�����治Ҫ���κεĴ�ӡ���
			throw new RuntimeException(e);
		}finally {
			close(null, pStatement, connection);
		}

	}
	// �ر���Դ 1.����� 2.������� 3.����
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
