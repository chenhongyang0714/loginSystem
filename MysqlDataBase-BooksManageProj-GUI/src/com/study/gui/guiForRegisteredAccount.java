package com.study.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class guiForRegisteredAccount extends JFrame {

	public static JPanel contentPane;
	public static JTextField txtAccount;
	public static JTextField txtPassword;
	public static JButton btnButton;

	String _userAccountString = "";
	String _userPasswordString = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiForRegisteredAccount frame = new guiForRegisteredAccount();
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
	public guiForRegisteredAccount() {
		setResizable(false);
		setTitle("Registered Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(135, 100, 360, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccount = new JLabel("\u8D26\u53F7\uFF1A");
		lblAccount.setBounds(64, 63, 41, 15);
		contentPane.add(lblAccount);
		
		txtAccount = new JTextField("学号");
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
		txtAccount.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				txtAccount.setText("");
			}
		});
		txtAccount.setBounds(115, 60, 112, 21);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u5BC6\u7801\uFF1A");
		lblPassword.setBounds(64, 122, 41, 15);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnButton.requestFocus();
					
					_userAccountString = txtAccount.getText();
					_userPasswordString = txtPassword.getText();
					if(_userPasswordString.equals("")) {
						JOptionPane.showMessageDialog(guiForRegisteredAccount.this, "密码不可以为空");
					} else if(!(_userAccountString.equals("")) && !(_userPasswordString.equals(""))) {
						Features features = new Features();
						features.registeredAccount(_userAccountString, _userPasswordString);
					}
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		txtPassword.setBounds(115, 119, 112, 21);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnButton = new JButton("Ensure");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_userAccountString = txtAccount.getText();
				_userPasswordString = txtPassword.getText();
				
				if(_userPasswordString.equals("")) {
					JOptionPane.showMessageDialog(guiForRegisteredAccount.this, "密码不可以为空");
				} else if(!(_userAccountString.equals("")) && !(_userPasswordString.equals(""))) {
					Features features = new Features();
					features.registeredAccount(_userAccountString, _userPasswordString);
				}
			}
		});
		btnButton.setForeground(Color.BLACK);
		btnButton.setBounds(108, 182, 97, 23);
		contentPane.add(btnButton);
	}
}
