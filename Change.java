package com;

import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.Component;


public class Change {
	
	private JFrame frame1;
	private String username = "weitianyi321"; //使用构造函数来读取MySql里面的信息
	public JLabel label1;
	private JLabel label2;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JPanel panel1 = new JPanel();
	
	/* definition related to Purchase.java */
	public JComboBox<String> getOnStation = new JComboBox<String>();
	public JComboBox<String> getOffStation = new JComboBox<String>();
	String station[] = {"Changsha Station", "Shumuling", "Xiangzhang Road", "Xiangfu Road", "Changsha Bus Station", "Citic City", "Ecological Zoo", "Hetang", "Bantang", "Xiangtan"};
	public JComboBox<String> departureTime = new JComboBox<String>();
	public TimeChooser datechooser = new TimeChooser();
	
	
	private LinkedList<String> Results = new LinkedList<>();
	private LinkedList<String> deptDateResults = new LinkedList<>(); //用链表存放信息以便显示在JList的时候用
	private LinkedList<String> deptStationResults = new LinkedList<>();
	private LinkedList<String> toStationResults = new LinkedList<>();
	
	private int ResultsCount = 1;
	private JComboBox<String> orderedTickets = new JComboBox<>();
	public JTable table;
	private JList list;
	
	public void go(){
		
		frame1 = new JFrame("改签界面");
		
		
		/**
		 * 下面这try里面用来读取结果集中的结果，并放入instance variable 里面的链表中
		 */
		try { 
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
			
			PreparedStatement ps = conn.prepareStatement("select TICK_NUM from tickinfo where USER_ID = ?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			    Results.addLast(String.valueOf(rs.getInt(ResultsCount)));
			}
			for(int i = 0; i < ResultsCount - 1; i++) {
				Results.addLast(rs.getString(i)); //把结果集放在链表里存储
			}
			
			
			/* Gets the date of the current user*/
			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
			String[] bookedInfo = new String[10]; // 新建一个字符串数组，在一会用ResultSet遍历结果集并保存在这里
		    list = new JList(bookedInfo);
		        
		    PreparedStatement ps2 = conn2.prepareStatement("select * from tickinfo where USER_ID = ?");
		    ps2.setString(1, username);
		    ResultSet rs2 = ps2.executeQuery();
		    while(rs2.next()) {
		        System.out.println(rs2.getString("DEPT_DATE"));
		    	deptDateResults.addLast(String.valueOf(rs2.getString("DEPT_DATE")));	
		    }
		    
		   
		    
		    /* Gets the departure station of the current user */
		    Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
		    PreparedStatement ps3 = conn3.prepareStatement("select DEPT_STATION from tickinfo where USER_ID = ?");
		    ps3.setString(1, username);
		    ResultSet rs3 = ps3.executeQuery();
		    while(rs3.next()) {
		    	System.out.println(rs3.getString("DEPT_STATION"));
		    	deptStationResults.addLast(String.valueOf(rs3.getString("DEPT_STATION")));
		    }
		    
		    
		    /* Gets the arrival station of the current user*/
		    Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
		    PreparedStatement ps4 = conn4.prepareStatement("select TO_STATION from tickinfo where USER_ID = ?");
		    ps4.setString(1, username);
		    ResultSet rs4 = ps4.executeQuery();
		    while(rs4.next()) {
		    	System.out.println(rs4.getString("TO_STATION"));
		    	toStationResults.addLast(String.valueOf(rs4.getString("TO_STATION")));
		    }
		    
		    /* Puts the aforementioned information into deprecated lists */
		    LinkedList<String> pointer = Results;
		    for(int i = 0; i < pointer.size(); i ++){
		        bookedInfo[i] = "票号: " + Results.get(i) + "  出发时间: " + deptDateResults.get(i) + "  出发站: " + deptStationResults.get(i) + "  到达站: " + toStationResults.get(i);
		        
		    } 
		    
		    /**
		     * 这个部分以下基本参照购票界面排版
		     */
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
			//System.out.println(ResultsCount + " " + rs.getString(1));
			//System.out.println(Results.get(0));
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		    for(int i = 0; i < Results.size(); i++) {
		    	orderedTickets.addItem("票号： " + String.valueOf(Results.get(i)));
		    }
		    
		    /*
		     * 在此处显示此人所有的票务信息
		     */
		    frame1.getContentPane().setLayout(null);
			label2 = new JLabel("乘车时间");
			label2.setBounds(68, 6, 52, 16);
			label4 = new JLabel("上车站点");
			label4.setBounds(68, 141, 52, 16);
			label5 = new JLabel("到达站点");
			label5.setBounds(68, 184, 52, 16);
			getOnStation.setBounds(133, 141, 386, 27);
			
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
			 getOffStation.setBounds(133, 180, 386, 27);
			 
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
			 departureTime.setBounds(342, 6, 166, 27);
			 
			 departureTime.addItem("06:00");
			 departureTime.addItem("08:00");
			 departureTime.addItem("10:00");
			 departureTime.addItem("12:00");
			 departureTime.addItem("14:00");
			 departureTime.addItem("16:00");
			 departureTime.addItem("18:00");
			 panel1.setSize(542, 262);
			 panel1.setLocation(20, 159);
			 panel1.setLayout(null);
			 datechooser = new TimeChooser();
			 datechooser.setBounds(133, 6, 197, 25);
			 
			 panel1.add(datechooser);
			 panel1.add(label2);
			 panel1.add(getOnStation);
			 panel1.add(getOffStation);
			 panel1.add(label4);
			 panel1.add(departureTime);
			 panel1.add(label5);
			 
	
	
			 
        //下面这句不加就啥也没有
			 frame1.getContentPane().add(BorderLayout.SOUTH, panel1);
			orderedTickets.setBounds(152, 120, 528, 27);
			frame1.getContentPane().add(orderedTickets);
			
        label1 = new JLabel("请您选择需要改签的票");
        label1.setBounds(10, 124, 130, 16);
        frame1.getContentPane().add(label1);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label6 = new JLabel("出发时间");
		
        
        JLabel label = new JLabel("尊敬的客户，以下是您还为出行的购票记录");
        label.setBounds(232, 6, 247, 16);
       
        
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //JScrollBar scrollBar = new JScrollBar();
        
        //scrollBar.setBounds(665, 24, 15, 76);
        //frame1.getContentPane().add(scrollBar);
        
      
        
        list.setBounds(10, 24, 670, 85);
        
        frame1.getContentPane().add(list);
        
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(665, 24, 15, 85);
        frame1.getContentPane().add(scrollBar);
        
        JButton button = new JButton("确认");
        button.setBounds(276, 606, 117, 29);
        frame1.getContentPane().add(button);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setSize(700, 700);
			frame1.setVisible(true);
			
		
		
		
	}
	
	public static void main(String[] args) {
		Change change1 = new Change();
		change1.go();
	}
}
