package com.study.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class guiForChangePassword extends JFrame {

	public static JPanel contentPane;  
	public static JTextField txtUserAccount;
	public static JPasswordField txtPassword;
	public static JPasswordField txtRePassword;
	
	public static String _txtUserAccount = "";
	public static String _txtPassword = "";
	public static String _txtRePassword = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiForChangePassword frame = new guiForChangePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guiForChangePassword() {
		setTitle("Change Password");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(860, 100, 360, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEnsure = new JButton("Ensure");
		btnEnsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_txtUserAccount = txtUserAccount.getText();
				_txtPassword = txtPassword.getText();
				_txtRePassword = txtRePassword.getText();
				
				if(_txtPassword.equals("") || _txtRePassword.equals("")) {
					JOptionPane.showMessageDialog(guiForChangePassword.this, "密码不可以为空");
				} else if(!(_txtPassword.equals(_txtRePassword))) {
					txtPassword.setText("");
					txtRePassword.setText("");
					JOptionPane.showMessageDialog(guiForChangePassword.this, "两次密码不相同");
				}  else if(_txtPassword.equals(_txtRePassword)  && !(_txtPassword.equals(""))) {
					Features features = new Features();
					features.changePassword(_txtUserAccount, _txtPassword);
					JOptionPane.showMessageDialog(guiForChangePassword.this, "修改成功");
				}
			}
		});
		btnEnsure.setBounds(108, 191, 97, 23);
		contentPane.add(btnEnsure);
		
		JLabel lblUserAccount = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblUserAccount.setBounds(87, 48, 48, 15);
		contentPane.add(lblUserAccount);
		
		txtUserAccount = new JTextField("学号");
		txtUserAccount.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPassword.requestFocus();
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		txtUserAccount.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				txtUserAccount.setText("");
			}
		});
		txtUserAccount.setBounds(139, 45, 107, 21);
		contentPane.add(txtUserAccount);
		txtUserAccount.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		lblPassword.setBounds(51, 88, 84, 15);
		contentPane.add(lblPassword);
		
		JLabel lblRePassword = new JLabel("\u518D\u6B21\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		lblRePassword.setBounds(38, 128, 97, 15);
		contentPane.add(lblRePassword);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtRePassword.requestFocus();
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		txtPassword.setBounds(139, 85, 107, 21);
		contentPane.add(txtPassword);
		
		txtRePassword = new JPasswordField();
		txtRePassword.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnEnsure.requestFocus();
					
					_txtUserAccount = txtUserAccount.getText();
					_txtPassword = txtPassword.getText();
					_txtRePassword = txtRePassword.getText();
					Features features = new Features();
					features.changePassword(_txtUserAccount, _txtPassword);
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		txtRePassword.setBounds(139, 125, 107, 21);
		contentPane.add(txtRePassword);
	}
}
