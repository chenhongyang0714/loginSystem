package com.study.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/** my tools
  * @author chy 
  * @date 8 Oct 2019 23:10:08 
  * @version 1.0  
*/

public class JDBCUtils {

	// ��Books���ݿ��������
	private static final String CONNECTONURL = "jdbc:mysql://localhost:3306/Books?useUnicode=true&characterEncoding=UTF8&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1300";
	
	// ��������
	public static Connection createConnection() {
		try {
			// ע������
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(CONNECTONURL, USERNAME, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	// �رմ�������Դ  Connection
	public static void closeConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement statement) {
		try {
			if(statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
