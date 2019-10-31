package com.study.gui;

/** 
  * @author chy 
  * @date 8 Oct 2019 22:51:51 
  * @version 1.0  
*/

public interface IBooksManage {

	// �����û���½ͼ��ݲ�ѯϵͳ
	public boolean LandingWithPassword();
	
	// �鿴ͼ����е������鼮
	public void SelectAllBooks();
	
	// ����������ѯ ͼ��
	public void SelectDesignatedBook();
	
	// �������� ����
	public void BorrowBookByName();
	
	// �������� ����
	public void RepayBookByName();
	
	// �������� ����
	public int RenewBookByName();
	
	
	// �����û�����������
	public void changePassword(String userAccount, String userPassword);
	
	// ע�����û�
	public void registeredAccount(String userAccount, String userPassword);
}
