package com;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
	public Login() {
	}
	
	JFrame frame1;
	JPanel panel1;
	
	public static final long serialVersionUID = 1L;
	
	private JTextField usernamein;
	private JTextField passwordin;

	private JLabel label1;
	private JLabel label2;
	
	
	
	void go(){
		
		frame1 = new JFrame();
		panel1 = new JPanel();
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JButton button1 = new JButton("登陆");
		
		button1.addActionListener(this);
		
		usernamein = new JTextField(20);
		passwordin = new JTextField(20);
		
		label1 = new JLabel("用户名");
		label2 = new JLabel("密码");
		
		panel1.add(label1);
		panel1.add(usernamein);
		panel1.add(label2);
		panel1.add(passwordin);
		

		frame1.getContentPane().add(BorderLayout.NORTH, panel1);
		frame1.getContentPane().add(BorderLayout.SOUTH, button1);
		frame1.setSize(600,300);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		
		Login lg1 = new Login();
		lg1.go();
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			//选择import java.sql.PreparedStatement
			//select * from 表示显示所有数据，所以这里用 select USER_ID from
			//用户名对的话rs.next()为true，没有这个用户名的话是false
			//rs.next()会不会导致指针自增加 会的
			//想要验证是否相等 一定要用* 把整个表都打出来
			
			PreparedStatement ps1;
			try {
				ps1 = conn.prepareStatement("select * from passinfo where USER_ID = ?");
				ps1.setString(1, usernamein.getText());
				ResultSet rs = ps1.executeQuery();
				String expectedPassword = null;
				String actualPassword = null;
				if(rs.next() == true){
				expectedPassword = rs.getString("USER_PASSWORD");
				actualPassword = passwordin.getText();
				if(expectedPassword.equals(actualPassword)){
				
				Home home1 = new Home(usernamein.getText());
				home1.go();
				
				
			    }
				
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*ResultSet cannot be printed/used only if we use the next() boolean pointer to point at
			next array! 不然会指出"before start of result set query"*/
			
			//System.out.println(rs.next());
			
			
			
		}
		}

		
				
			
	
