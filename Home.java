package com;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;


public class Home {
	
	JFrame frame2;
	JPanel panel2;
	private String username;
	public Home(String s) {
		username = s;
	}
	public Home() {
		
	}
	public static final long serialVersionUID = 1L;
	
	void go(){
		frame2 = new JFrame();
		frame2.setBackground(Color.CYAN);
		panel2 = new JPanel();
		JButton button3 = new JButton("退票");
		button3.setBounds(120, 200, 166, 29);
		button3.addActionListener(new Button3Listener());
		JButton button2 = new JButton("改签");
		button2.setBounds(120, 164, 166, 29);
		button2.addActionListener(new Button2Listener());
		
		JButton button1 = new JButton("购票");
		button1.setBounds(120, 128, 166, 29);
		button1.addActionListener(new Button1Listener());
		frame2.getContentPane().setLayout(null);
		frame2.getContentPane().add(button1);
		frame2.getContentPane().add(button2);
		frame2.getContentPane().add(button3);
		JButton button4 = new JButton("退出登录");
		button4.setBounds(153, 343, 96, 29);
		button4.addActionListener(new Button4Listener());
		frame2.getContentPane().add(button4);
		
		JLabel label = new JLabel("欢迎您！请选择您需要的操作！");
		label.setForeground(Color.RED);
		label.setBounds(116, 30, 182, 29);
		frame2.getContentPane().add(label);
		
		frame2.setSize(400, 400);
		frame2.setVisible(true);
		
		
		
		
	}
	//购票按钮触发事件
	class Button1Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			Purchase purchase = new Purchase(username);
			purchase.go();
			
		}
	}
	
	//改签按钮触发事件
	
	class Button2Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			Change change1 = new Change();
			change1.go();
			
		}
	}
	
	//退票按钮触发事件
	
	class Button3Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	//退出登录按钮触发事件
	
	class Button4Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			
			Login loginback = new Login();
			loginback.go();
			
		}
	}
	public static void main(String[] args){
		Home home1 = new Home();
		home1.go();
		
		
	}
}
