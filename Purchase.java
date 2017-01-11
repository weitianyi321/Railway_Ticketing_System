package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.SystemColor;

/**
 * 
 * @author kevinwei
 *
 */

public class Purchase implements ActionListener{
	
	public Purchase(String username) {
	    this.username = username;
		
	}
	
	public Purchase() {
		
	}
	
	public static final long serialVersionUID = 1L;
	
	public java.sql.Date sqlDate;
	
	private JFrame frame1;
	private JPanel panel1;
	
	 private JLabel label1;
	//private JLabel label1;
	 private JLabel label2;
	 private JLabel label3;
	 private JLabel label4;
	
	 public JComboBox<String> getOnStation = new JComboBox<String>();
	 public JComboBox<String> getOffStation = new JComboBox<String>();
	 String station[] = {"Changsha Station", "Shumuling", "Xiangzhang Road", "Xiangfu Road", "Changsha Bus Station", "Citic City", "Ecological Zoo", "Hetang", "Bantang", "Xiangtan"};
	
	 public JComboBox<String> departureTime = new JComboBox<String>();
	 public TimeChooser datechooser = new TimeChooser();
	 private String username;
	 
	 
	 
	public void go() {
		
		frame1 = new JFrame("购票页面");
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 800, 216);
		
		label1 = new JLabel("乘车时间");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2 = new JLabel("上车站点");
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		label3 = new JLabel("到达站点");
		label3.setAlignmentX(Component.CENTER_ALIGNMENT);
		label4 = new JLabel("出发时间");
		label4.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Some instantiation of ComboBoxes
		
		
		getOnStation.addItem("长沙站 Changsha Station");
		getOnStation.addItem("树木岭 Shumuling");
		getOnStation.addItem("香樟路 Xiangzhang Road");
		getOnStation.addItem("湘府路 Xiangfu Road");
		getOnStation.addItem("长沙汽车南站 Changsha Bus Station");
		getOnStation.addItem("中信新城 Citic City");
		getOnStation.addItem("生态动物园 Ecological Zoo");
		getOnStation.addItem("荷塘 Hetang");
		getOnStation.addItem("板塘 Bantang");
		getOnStation.addItem("湘潭 Xiangtan");
		
		getOffStation.addItem("长沙站 Changsha Station");
		getOffStation.addItem("树木岭 Shumuling");
		getOffStation.addItem("香樟路 Xiangzhang Road");
		getOffStation.addItem("湘府路 Xiangfu Road");
		getOffStation.addItem("长沙汽车南站 Changsha Bus Station");
		getOffStation.addItem("中信新城 Citic City");
		getOffStation.addItem("生态动物园 Ecological Zoo");
		getOffStation.addItem("荷塘 Hetang");
		getOffStation.addItem("板塘 Bantang");
		getOffStation.addItem("湘潭 Xiangtan");
		
		departureTime.addItem("06:00");
		departureTime.addItem("08:00");
		departureTime.addItem("10:00");
		departureTime.addItem("12:00");
		departureTime.addItem("14:00");
		departureTime.addItem("16:00");
		departureTime.addItem("18:00");
		
		
		
		
		
		panel1.setBackground(SystemColor.window);
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JButton button1 = new JButton("确认订单");
		button1.setBounds(231, 443, 335, 29);
		button1.addActionListener(this);
		
		
		
		
	   
		/**
		 * DateChooser Commented from http://blog.sina.com.cn/s/blog_7a4d0df90101d7bd.html
		 * Author:夏夜雨声
		 */
	    frame1.getContentPane().setLayout(null);
				
	    panel1.add(label1);
		panel1.add(datechooser);
		panel1.add(label2);
		panel1.add(getOnStation);
		panel1.add(label3);
		panel1.add(getOffStation);
		panel1.add(label4);
		panel1.add(departureTime);
		
	
	
		
        //下面这句不加就啥也没有
		frame1.getContentPane().add(panel1);
		frame1.getContentPane().add(button1);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(800,500);
		frame1.setVisible(true);

	}
	
	public static void main(String[] args) {
		
		Purchase purchase1 = new Purchase("weitianyi321");
		purchase1.go();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
		/*
		 * insert into tickinfo(TICK_NUM, TRAIN_NUM , DEPT_DATE ,USER_ID, DEPT_STATION, TO_STATION ) values(2,1,'2016-05-10','weitianyi321','WXA','JLL');
		 */
		try {
			
			/* This gets the value of selected ComboBox value (e.g. Changsha Station -> 1, 
			 * e.g. Shumuling -> 2)*/
			int id1 = getOnStation.getSelectedIndex();
			int id2 = getOffStation.getSelectedIndex();
			Date date1 = (Date) datechooser.getDate();
			sqlDate = new java.sql.Date(date1.getTime());
			
			
			
			/* 06:00 train is train 1, 08:00 train is train 2, and so on */
			int trainNum = departureTime.getSelectedIndex();
			
			System.out.println("The station you get on is" + station[id1]);
			System.out.println("The station you get off is" + station[id2]);
			
			PreparedStatement ps1 = conn.prepareStatement("insert into tickinfo(TICK_NUM, TRAIN_NUM , DEPT_DATE ,USER_ID, DEPT_STATION, TO_STATION ) values(?,?,?,?,?,?);");
			ps1.setInt(1, 0);
			ps1.setInt(2, trainNum);
			ps1.setDate(3, sqlDate);
			ps1.setString(4, username);
			ps1.setString(5, station[id1]);
			ps1.setString(6, station[id2]);
			ps1.executeUpdate();
			System.out.println(ps1.toString());
			
			
			
			
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}

}
}
