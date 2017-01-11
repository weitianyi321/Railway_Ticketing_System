package com;
import javax.swing.*;   



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Register implements ActionListener  {
	/**
	 * 因为JLabel不需要别人修改所以变成Private类型
	 */
	
	public static final long serialVersionUID = 1L;

	
	private JTextField username;
	private JTextField password;
	private JRadioButton sexM;
    private JRadioButton sexF;
    private JTextField IDNumber;
    private JTextField RealName;
    
    JFrame frame;
    
	private JLabel lb1;
	private JLabel lb2;
	private JLabel lb3;
	private JLabel lb4;
	private JLabel lb5;
	
	public void go() {
		frame = new JFrame();
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.GRAY);
		
		//Setting the BoxLayout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton button = new JButton("提交");
		
		button.addActionListener(this);
		
		username = new JTextField(20);
	    password = new JTextField(20);
	    IDNumber = new JTextField(20);
	    RealName = new JTextField(20);
	   
	    sexM = new JRadioButton("男");
	    sexF = new JRadioButton("女");
	    ButtonGroup bg = new ButtonGroup();
	    bg.add(sexM);
	    bg.add(sexF);
    
	    
	    lb1 = new JLabel("用户名");
	    lb2 = new JLabel("密码");
	    lb3 = new JLabel("性别");
	    lb4 = new JLabel("身份证号");
	    lb5 = new JLabel("真实姓名");
	    
	    		
	    //The programming serial must obey the exact monitoring effect
	    //the labels must match the JTextField Objects
	    panel.add(lb1);
	    panel.add(username); 
	    panel.add(lb2);
	    panel.add(password);	
	    panel.add(lb3);
	    panel.add(sexM);
	    panel.add(sexF);
	    panel.add(lb4);
	    panel.add(IDNumber);
	    panel.add(lb5);
	    panel.add(RealName);
	    
		frame.getContentPane().add(BorderLayout.NORTH, panel);
		//frame.getContentPane().add(BorderLayout.NORTH, username);
		//frame.getContentPane().add(BorderLayout.NORTH, password);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		frame.setSize(300, 500);
		frame.setVisible(true);
		frame.setTitle("新用户注册");
	
	}

	public void actionPerformed(ActionEvent e) {  
		
			Connection conn = null;
			try { 
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(); 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
			
				/**
				 * 使用PreparedStatement类，以用来初始化SQL语句
				 * 不使用这个类可能导致编译错误
				 * 
				 */
								
				PreparedStatement ps1 = conn.prepareStatement("insert into passinfo(USER_ID,USER_PASSWORD,USER_SEX,USER_IDNUM,USER_REALNAME) values(?,?,?,?,?)");
				ps1.setString(1, username.getText());
				ps1.setString(2, password.getText());
				
				//DECIDE WHICH RADIO BUTTON IS CHOSEN
				if(sexM.isSelected()){
					ps1.setString(3, sexM.getText());
				} else{
				
				ps1.setString(3, sexF.getText());
				}
				System.out.println("Male interface: "+sexM.getText()+"Female interface: "+sexF.getText());
			
				ps1.setString(4, IDNumber.getText());
				ps1.setString(5, RealName.getText());
				
				ps1.executeUpdate();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}

	public static void main(String[] args) {
	     Register gui = new Register();
	     gui.go();
				
	}
	
}
