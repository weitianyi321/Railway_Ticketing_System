package com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/*如果下面这行中不写 implements ActionListener 的话就不会要求必须写actionPerformed 函数*/
public class Main extends JFrame  {
	public Main() {
		setBackground(Color.PINK);
		getContentPane().setForeground(Color.GREEN);
	}
	//The main method here requires two buttons, each one of these buttons are
	//hearing from user's clicking actions.
	//Instead of using one single class, we implement two classes in the main class body
	//the addActionListener() method shall be using the different two newly created classes
	//as arguments
	
	JFrame frame1;
	JPanel panel1;
	JLabel backgroundlabel1;
	private String username;
	
	public static final long serialVersionUID = 1L;
	
	
	void go(){
		
		frame1 = new JFrame("欢迎使用购票系统");
		panel1 = new JPanel();
		
		
		/*add background image here*/
//		ImageIcon bg = new ImageIcon("/Users/kevinwei/Documents/workspace/Railway/file/background.jpg"); 
//		
//		backgroundlabel1 = new JLabel(bg);
//		backgroundlabel1.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
//      把装载了图片的标签设置在最底层
//		frame1.getLayeredPane().add(backgroundlabel1, new Integer(Integer.MIN_VALUE));
		
		JButton button1 = new JButton("新用户注册");
		JButton button2 = new JButton("老用户登录");
		
		panel1.add(button1);
		panel1.add(button2);
		button1.addActionListener(new Button1Listener());
		button2.addActionListener(new Button2Listener());
		//button2.addActionListener(this);
		
		frame1.getContentPane().add(BorderLayout.EAST, panel1);
		frame1.getContentPane().add(BorderLayout.SOUTH, panel1);
	
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(300,100);
		frame1.setVisible(true);
		
		
	
	}
	

	
	
	/**
	 * This is where I plan to start writing the two classes and destroy everything I have just done.
	 * YEAH
	 * 
	 * FOR MORE INFORMATION: the newly created classes will automatically notify you to build 
	 * actionPerformed methods(May be the eclipse software secretly knows what I am doing right now?
	 * @param args
	 */
	
	class Button1Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Register rg2 = new Register();
			rg2.go();
			
			
			
		}
		
	}
	
	class Button2Listener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){
			Login lg1 = new Login();
			lg1.go();
		
					
			}
	}
	
	
    public static void main(String[] args) {
		Main main1 = new Main();
		main1.go();
	}
	
	

}
