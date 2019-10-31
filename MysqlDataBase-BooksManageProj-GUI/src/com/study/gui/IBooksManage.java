package com.study.gui;

/** 
  * @author chy 
  * @date 8 Oct 2019 22:51:51 
  * @version 1.0  
*/

public interface IBooksManage {

	// 用于用户登陆图书馆查询系统
	public boolean LandingWithPassword();
	
	// 查看图书馆中的所有书籍
	public void SelectAllBooks();
	
	// 根据书名查询 图书
	public void SelectDesignatedBook();
	
	// 根据书名 借书
	public void BorrowBookByName();
	
	// 根据书名 还书
	public void RepayBookByName();
	
	// 根据书名 续借
	public int RenewBookByName();
	
	
	// 根据用户名更改密码
	public void changePassword(String userAccount, String userPassword);
	
	// 注册新用户
	public void registeredAccount(String userAccount, String userPassword);
}
