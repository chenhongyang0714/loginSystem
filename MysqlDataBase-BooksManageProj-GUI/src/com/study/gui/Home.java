package com.study.gui;

import java.util.Scanner;

/** 
  * @author chy 
  * @date 9 Oct 2019 16:40:03 
  * @version 1.0  
*/

public class Home {

	static Features features = new Features();
	static Boolean whetherBoolean = false;  // 用于 记录  是否成功登陆


	// 注意 静态方法 中的  类的实例 也要是静态的
	public static void gameRunning() {
		
		int select = -1;
		
		while(select != 0) {
			System.out.println("-------Library System-------");
			System.out.println("   1 -- Landing yourself");
			System.out.println("   2 -- View all books in the library");
			System.out.println("   3 -- Query book by name");
			System.out.println("   4 -- Borrow book by name");
			System.out.println("   5 -- Repay book by name");
			System.out.println("   6 -- Renew book by name");
			System.out.println("   7 -- Change password by userAccount");
			System.out.println("   0 -- Exit");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			do {
				System.out.println("Please make a choice:");
				select = scanner.nextInt();
			} while(select <0 || select > 7);
			
			switch(select) {
			case 1:
				if(whetherBoolean == false) {
				whetherBoolean = features.LandingWithPassword();
//				System.out.println(whetherBoolean);  // 如果登陆成功 则为 true(实际为: 强制运行登陆程序，就 ok)					
				} else {
					System.out.println("您已登录，无需重新登陆!");
				}
				break;
			case 2:
				if(whetherBoolean == false) {  // 控制 --> 如果没有登入用户，就不能执行其他功能
					System.out.println("First please landing yourself!!! \n");
				} else {
					features.SelectAllBooks();
				}
				break;	
			case 3:
				if(whetherBoolean == false) {
					System.out.println("First please landing yourself!!! \n");
				} else {
					features.SelectDesignatedBook();
				}
				break;
			case 4:
				if(whetherBoolean == false) {
					System.out.println("First please landing yourself!!! \n");
				} else {
					features.BorrowBookByName();
				}
				break;	
			case 5:
				if(whetherBoolean == false) {
					System.out.println("First please landing yourself!!! \n");
				} else {
					features.RepayBookByName();
				}
				break;
			case 6:
				if(whetherBoolean == false) {
					System.out.println("First please landing yourself!!! \n");
				} else {
					features.RenewBookByName();
				}
				break;
			case 7:
				if(whetherBoolean == false) {
					System.out.println("First please landing yourself!!! \n");
				} else {
					String userAccount = "";
					String userPassword = "";
					Scanner scanner2 = new Scanner(System.in);
					System.out.println("请输入您想更改的账户名和新密码:");
					userAccount = scanner2.next();
					userPassword = scanner2.next();
					features.changePassword(userAccount, userPassword);
				}
				break;
			case 0: 
				System.out.println("Bye and Miss you!");
				System.exit(0);
			}
		}
		
	}
	
	
}
