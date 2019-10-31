package com.study.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Scanner;
import java.util.Date;


/**  ͨ��sql��ѯ���ݿ�Mysql����鼮
  * @author chy 
  * @date 8 Oct 2019 22:59:48 
  * @version 1.0  
*/

public class Features implements IBooksManage {

	Date date = null;  // ��¼����ʱ��ʱ��(ͬʱ����Ƿ��ڽ���״̬)
	
	String userNumber = "";  // ���ڽ��� �û������ ��Ϣ
	String userName = "";
	
	// �����û���½ͼ��ݲ�ѯϵͳ
	@SuppressWarnings("deprecation")
	public boolean LandingWithPassword() {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCUtils.createConnection();
			
			statement = connection.createStatement();
			
			// ����Eclipseƽ̨
//			System.out.print("����������ѧ��������: \n");
//			@SuppressWarnings("resource")
//			Scanner scanner = new Scanner(System.in);
//			userNumber = scanner.next();
//			userName = scanner.next();
			
			// ����GUI
			userNumber = guiForLogin.txtAccount.getText();
			userName = guiForLogin.txtPassword.getText();
			
			String sQlString = "select * from users where userNumber = '"+userNumber+"' and userName = '"+userName+"'";
			resultSet = statement.executeQuery(sQlString);  // ִ��sql��䣬������ִ�н��
			
			if(resultSet.next()) {
				System.out.println("ͼ���ѯϵͳ--��½�ɹ���");
				return true;
			} else {
				System.out.println("ͼ���ѯϵͳ--��½ʧ�ܣ�");
				System.out.println("��������ѧ�ź��û��������µ�½��");
				return false;
//				LandingWithPassword();  // ���µ�½
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
	
	// �鿴ͼ����е������鼮
	@Override
	public void SelectAllBooks() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		 try {
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();  // ����ִ��sql���
			resultSet = statement.executeQuery("select * from BooksStorage");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("id") + "��" + resultSet.getString("bookName") + "��" 
			                                            + resultSet.getString("bookAuthor") + "��" + resultSet.getString("bookPress") 
			                                            + "��" + "��" +resultSet.getString("bookNumber") + "��");
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(statement);
			JDBCUtils.closeConnection(connection);
			}
			
		}

	// ����������ѯ ͼ��
	public void SelectDesignatedBook() {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String bookName = "";
		
		try {
			connection = JDBCUtils.createConnection();
			
			statement = connection.createStatement();
			
			System.out.println("��������Ҫ��ѯ������:");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString = "select * from booksstorage where bookName = '"+bookName+"'";
			
			resultSet = statement.executeQuery(sQLString);
			
			if(resultSet.next()) {
				System.out.println("��ѯ�ɹ�!");
				System.out.println("�������Ϣ����:");
				System.out.println(resultSet.getString("bookName") + "��" 
							+ resultSet.getString("bookAuthor") + "��" + resultSet.getString("bookPress") + "��" 
							+ "��" + resultSet.getString("bookNumber") + "��");
			} else {
				System.out.println("��ѯʧ��!");
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
  
    // �������� ����
	public void BorrowBookByName() {
		/**
		 * ע�⣺update ִ����� Ҫ�� prepareStatement
		 *      select ִ�����  Ҫ�� statement
		 */
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		String bookName = "";
		String laveOfBooks = "";  // ʣ�������
		int updateRresult = 0;
		
		try {
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();
			
			System.out.println("��������Ҫ�������:    ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString1 = "select * from booksStorage where bookName = '" + bookName + "'";
			resultSet = statement.executeQuery(sQLString1);
			
			// �ж���û�д���
			if(resultSet.next()) {  // ע��:��ʼʱ resultSet ��ָ��
				System.out.println("�����и���ʣ��:" + resultSet.getString("bookNumber") + "��");
				laveOfBooks = resultSet.getString("bookNumber");
			} else {
				System.out.println("ϵͳ������������������,����������!");
				BorrowBookByName();
			}
			
			// ���ҵ����������ж��Ƿ���ʣ����
			if(!laveOfBooks.equals("0")) {
				String sQLString2 = "update booksStorage set bookNumber = bookNumber - 1 where bookName = '" + bookName + "'";
				preparedStatement = connection.prepareStatement(sQLString2);
				updateRresult = preparedStatement.executeUpdate();
				if(updateRresult > 0) {
					System.out.println("���ĳɹ�");
					date = new Date();
					System.out.printf("��ǰʱ��Ϊ: %ty��%<tm��%<td�� \n", date);
					System.out.println("��������30�����!");
					System.out.println("лл����^_^");
				}
			}else {
				System.out.println("�ǳ���Ǹ,��������,����ʧ��!");
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
	
	// �������� ����
	public void RepayBookByName() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String bookName = "";  // ��Ź黹���������
		int updateResult = 0;
		
		try {
			connection = JDBCUtils.createConnection();
			
			System.out.println("��������Ҫ�黹������:");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString = "update booksstorage set bookNumber = bookNumber + 1 where bookName = '" + bookName + "'";
			preparedStatement = connection.prepareStatement(sQLString);
			
			updateResult = preparedStatement.executeUpdate();
			
			if(updateResult > 0) {
				System.out.println("�ɹ��黹��!");
				date = null;
			} else {
				System.out.println("ϵͳ������������������,����������!");
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

	// �������� ����
	public int RenewBookByName() {
		
		if(date == null) {
			System.out.println("���Ƚ��飬Ȼ������!");
			return 0;
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String bookName = "";
		
		try {
			connection = JDBCUtils.createConnection();
			statement = connection.createStatement();
			
			System.out.println("�������������������!");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			bookName = scanner.next();
			
			String sQLString = "select * from booksstorage where bookName = '" + bookName + "'";
			
			resultSet = statement.executeQuery(sQLString);
			
			if(resultSet.next() != false) {
				System.out.println("����ɹ�!");
				System.out.printf("���ϴν���ʱ��Ϊ: %tY��%<tm��%<td�� \n", date);
				System.out.println("��������60�����!");
				System.out.println("лл����^_^");
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
	
	
	// �����û�����������
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
