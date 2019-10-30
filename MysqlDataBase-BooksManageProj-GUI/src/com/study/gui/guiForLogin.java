package com.study.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class guiForLogin extends JFrame {

	public static JPanel contentPane;
	public static JTextField txtAccount;
	public static JLabel lblPassword;
	public static JButton btnEnsure;
	public static JPasswordField txtPassword;
	public static boolean whether;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiForLogin frame = new guiForLogin();
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
	public guiForLogin() {
		setResizable(false);  // 设置窗口大小不可改变
		setTitle("loginSystem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 282);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccount = new JLabel("\u8D26\u53F7\uFF1A");
		lblAccount.setBounds(76, 44, 58, 15);
		contentPane.add(lblAccount);
		
		txtAccount = new JTextField();
		txtAccount.addKeyListener(new KeyListener() {
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
		txtAccount.setBounds(144, 41, 97, 21);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		lblPassword = new JLabel("\u5BC6\u7801\uFF1A");
		lblPassword.setBounds(76, 88, 58, 15);
		contentPane.add(lblPassword);
		
		btnEnsure = new JButton("Ensure");
		btnEnsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Features features = new Features();
				whether = features.LandingWithPassword();
				System.out.println(whether); 
			}
		});
		btnEnsure.setBounds(37, 178, 77, 23);
		contentPane.add(btnEnsure);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiForLogin.this, "Bye");
				System.out.println("Bye");
				System.exit(0);
			}
		});
		btnExit.setBounds(271, 178, 59, 23);
		contentPane.add(btnExit);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {  // 按键释放
			}
			public void keyPressed(KeyEvent e) {  // 按键按下
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEnsure.requestFocus();  // 获取光标
					
					Features features = new Features();
					whether = features.LandingWithPassword();
					System.out.println(whether); 
				}
			}
		});
		txtPassword.setBounds(144, 85, 97, 21);
		contentPane.add(txtPassword);
		
		JLabel lblIdentity = new JLabel("\u8EAB\u4EFD\u4FE1\u606F\uFF1A");
		lblIdentity.setBounds(65, 133, 77, 15);
		contentPane.add(lblIdentity);
		
		JRadioButton rBTeacher = new JRadioButton("\u6559\u5E08");
		rBTeacher.setBounds(126, 129, 58, 23);
		contentPane.add(rBTeacher);
		
		JRadioButton rBStudent = new JRadioButton("\u5B66\u751F");
		rBStudent.setBounds(186, 129, 127, 23);
		contentPane.add(rBStudent);
		
		JButton btnChangePassword = new JButton("ChangePassword");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiForChangePassword frame = new guiForChangePassword();
				frame.setVisible(true);
			}
		});
		btnChangePassword.setBounds(123, 178, 138, 23);
		contentPane.add(btnChangePassword);
		
		// 给Ensure键添加监听
		btnEnsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(whether == true) {
					JOptionPane.showMessageDialog(guiForLogin.this, "登陆成功");
				}
				if(whether == false) {
					JOptionPane.showMessageDialog(guiForLogin.this, "登陆失败 请重新登录");
				}
			}
		});
	}
}
