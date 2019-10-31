package com.study.gui;

import java.util.Scanner;

/** 
  * @author chy 
  * @date 9 Oct 2019 16:40:03 
  * @version 1.0  
*/

public class Home {

	static Features features = new Features();
	static Boolean whetherBoolean = false;  // ���� ��¼  �Ƿ�ɹ���½


	// ע�� ��̬���� �е�  ���ʵ�� ҲҪ�Ǿ�̬��
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
//				System.out.println(whetherBoolean);  // �����½�ɹ� ��Ϊ true(ʵ��Ϊ: ǿ�����е�½���򣬾� ok)					
				} else {
					System.out.println("���ѵ�¼���������µ�½!");
				}
				break;
			case 2:
				if(whetherBoolean == false) {  // ���� --> ���û�е����û����Ͳ���ִ����������
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
					System.out.println("������������ĵ��˻�����������:");
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
