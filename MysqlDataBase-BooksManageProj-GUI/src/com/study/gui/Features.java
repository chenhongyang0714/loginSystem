package com.study.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Scanner;
import java.util.Date;


/**  通过sql查询数据库Mysql里的书籍
  * @author chy 
  * @date 8 Oct 2019 22:59:48 
  * @version 1.0  
*/

public class Features implements IBooksManage {

	Date date = null;  // 记录借书时的时间(同时标记是否处于借书状态)
	
	String userNumber = "";  // 用于接收 用户输入的 信息
	String userName = "";
	
	// 用于用户登陆图书馆查询系统
	@SuppressWarnings("deprecation")
	public boolean LandingWithPassword() {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCUtils.createConnection();
			
			statement = connection.createStatement();
			
			// 基于Eclipse平台
//			System.out.print("请输入您的学号与姓名: \n");
//			@SuppressWarnings("resource")
//			Scanner scanner = new Scanner(System.in);
//			userNumber = scanner.next();
//			userName = scanner.next();
			
			// 基于GUI
			userNumber = guiForLogin.txtAccount.getText();
			userName = guiForLogin.txtPassword.getText();
			
			String sQlString = "select * from users where userNumber = '"+userNumber+"' and userName = '"+userName+"'";
			resultSet = statement.executeQuery(sQlString);  // 执行sql语句，并返回执行结果
			
			if(resultSet.next()) {
				System.out.println("图书查询系统--登陆成功！");
				return true;
			} else {
				System.out.println("图书查询系统--登陆失败！");
				System.out.println("请检查您的学号和用户名并重新登陆！");
				return false;
//				LandingWithPassword();  // 重新登陆
			}
 			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(statement);
			JDBCUtils.closeConnection(connection);
		}
		
		return true;
		
	}
	
	// 查看图书馆中的所有书籍
	@Override
	public void SelectAllBooks() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		 try {
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();  // 用来执行sql语句
			resultSet = statement.executeQuery("select * from BooksStorage");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("id") + "，" + resultSet.getString("bookName") + "，" 
			                                            + resultSet.getString("bookAuthor") + "，" + resultSet.getString("bookPress") 
			                                            + "，" + "共" +resultSet.getString("bookNumber") + "本");
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(statement);
			JDBCUtils.closeConnection(connection);
			}
			
		}

	// 根据书名查询 图书
	public void SelectDesignatedBook() {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String bookName = "";
		
		try {
			connection = JDBCUtils.createConnection();
			
			statement = connection.createStatement();
			
			System.out.println("请输入您要查询的书名:");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString = "select * from booksstorage where bookName = '"+bookName+"'";
			
			resultSet = statement.executeQuery(sQLString);
			
			if(resultSet.next()) {
				System.out.println("查询成功!");
				System.out.println("该书的信息如下:");
				System.out.println(resultSet.getString("bookName") + "，" 
							+ resultSet.getString("bookAuthor") + "，" + resultSet.getString("bookPress") + "，" 
							+ "共" + resultSet.getString("bookNumber") + "本");
			} else {
				System.out.println("查询失败!");
				SelectDesignatedBook();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(statement);
			JDBCUtils.closeConnection(connection);
		}
	}
  
    // 根据书名 借书
	public void BorrowBookByName() {
		/**
		 * 注意：update 执行语句 要用 prepareStatement
		 *      select 执行语句  要用 statement
		 */
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		String bookName = "";
		String laveOfBooks = "";  // 剩余的书数
		int updateRresult = 0;
		
		try {
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();
			
			System.out.println("请输入您要借的书名:    ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString1 = "select * from booksStorage where bookName = '" + bookName + "'";
			resultSet = statement.executeQuery(sQLString1);
			
			// 判断有没有此书
			if(resultSet.next()) {  // 注意:初始时 resultSet 的指向
				System.out.println("本馆中该书剩余:" + resultSet.getString("bookNumber") + "本");
				laveOfBooks = resultSet.getString("bookNumber");
			} else {
				System.out.println("系统检测您输入的书名有误,请重新输入!");
				BorrowBookByName();
			}
			
			// 在找到书的情况下判断是否还有剩余书
			if(!laveOfBooks.equals("0")) {
				String sQLString2 = "update booksStorage set bookNumber = bookNumber - 1 where bookName = '" + bookName + "'";
				preparedStatement = connection.prepareStatement(sQLString2);
				updateRresult = preparedStatement.executeUpdate();
				if(updateRresult > 0) {
					System.out.println("借阅成功");
					date = new Date();
					System.out.printf("当前时间为: %ty年%<tm月%<td日 \n", date);
					System.out.println("请于最晚30天后还书!");
					System.out.println("谢谢合作^_^");
				}
			}else {
				System.out.println("非常抱歉,书量不足,借阅失败!");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closePreparedStatement(preparedStatement);
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(statement);
			JDBCUtils.closeConnection(connection);
		}
	}
	
	// 根据书名 还书
	public void RepayBookByName() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String bookName = "";  // 存放归还的书的书名
		int updateResult = 0;
		
		try {
			connection = JDBCUtils.createConnection();
			
			System.out.println("请输入您要归还的书名:");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString = "update booksstorage set bookNumber = bookNumber + 1 where bookName = '" + bookName + "'";
			preparedStatement = connection.prepareStatement(sQLString);
			
			updateResult = preparedStatement.executeUpdate();
			
			if(updateResult > 0) {
				System.out.println("成功归还书!");
				date = null;
			} else {
				System.out.println("系统检测您输入的书名有误,请重新输入!");
				RepayBookByName();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closePreparedStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
	} 

	// 根据书名 续借
	public int RenewBookByName() {
		
		if(date == null) {
			System.out.println("请先借书，然后续借!");
			return 0;
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String bookName = "";
		
		try {
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();
			
			System.out.println("请输入您想续借的书名!");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString = "select * from booksstorage where bookName = '" + bookName + "'";
			
			resultSet = statement.executeQuery(sQLString);
			
			if(resultSet.next() != false) {
				System.out.println("续借成功!");
				System.out.printf("您上次借阅时间为: %tY年%<tm月%<td日 \n", date);
				System.out.println("请于最晚60天后还书!");
				System.out.println("谢谢合作^_^");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(statement);
			JDBCUtils.closeConnection(connection);
		}
		return 0;
		
	}
	
	
	// 根据用户名更改密码
	public void changePassword(String userAccount, String userPassword) {
		
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {  
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closePreparedStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
	}
}
