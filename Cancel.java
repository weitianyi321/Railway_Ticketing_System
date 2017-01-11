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
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.Component;


public class Cancel implements ActionListener {
	
	private JFrame frame1;
	private String username = "weitianyi321"; //使用构造函数来读取MySql里面的信息
	public JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	private LinkedList<String> Results = new LinkedList<>();
	private LinkedList<String> deptDateResults = new LinkedList<>(); //用链表存放信息以便显示在JList的时候用
	private LinkedList<String> deptStationResults = new LinkedList<>();
	private LinkedList<String> toStationResults = new LinkedList<>();
	
	private int ResultsCount = 1;
	private JComboBox<String> orderedTickets = new JComboBox<>();
	public ArrayList<String> orderedTicketsIndicator = new ArrayList<>(); //存储combobox的对应票号数据
	int chosenTicketNumber = 9999;
	public JTable table;
	private JList list;
	
	public void go(){
		
		frame1 = new JFrame("退票界面");
		
		
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
		        //System.out.println(rs2.getString("DEPT_DATE"));
		    	deptDateResults.addLast(String.valueOf(rs2.getString("DEPT_DATE")));	
		    }
		    
		   
		    
		    /* Gets the departure station of the current user */
		    Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
		    PreparedStatement ps3 = conn3.prepareStatement("select DEPT_STATION from tickinfo where USER_ID = ?");
		    ps3.setString(1, username);
		    ResultSet rs3 = ps3.executeQuery();
		    while(rs3.next()) {
		    	//System.out.println(rs3.getString("DEPT_STATION"));
		    	deptStationResults.addLast(String.valueOf(rs3.getString("DEPT_STATION")));
		    }
		    
		    
		    /* Gets the arrival station of the current user*/
		    Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
		    PreparedStatement ps4 = conn4.prepareStatement("select TO_STATION from tickinfo where USER_ID = ?");
		    ps4.setString(1, username);
		    ResultSet rs4 = ps4.executeQuery();
		    while(rs4.next()) {
		    	//System.out.println(rs4.getString("TO_STATION"));
		    	toStationResults.addLast(String.valueOf(rs4.getString("TO_STATION")));
		    }
		    
		    /* Puts the aforementioned information into deprecated lists */
		    LinkedList<String> pointer = Results;
		    for(int i = 0; i < pointer.size(); i ++){
		        bookedInfo[i] = "票号: " + Results.get(i) + "  出发时间: " + deptDateResults.get(i) + "  出发站: " + deptStationResults.get(i) + "  到达站: " + toStationResults.get(i);
		        
		    } 
		    
		    
		    
		    
		    
		    
		    
		    
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
		    	orderedTicketsIndicator.add(String.valueOf(Results.get(i))); // 把票号数据加到队尾
		    }
		    
		    /*
		     * 在此处显示此人所有的票务信息
		     */
		    frame1.getContentPane().setLayout(null);
			orderedTickets.setBounds(102, 149, 528, 27);
			frame1.getContentPane().add(orderedTickets);
			
        label1 = new JLabel("请您选择需要取消的行程");
        label1.setBounds(271, 121, 152, 16);
        frame1.getContentPane().add(label1);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel label = new JLabel("以下是您还未出行的购票记录");
        label.setBounds(269, 6, 247, 16);
        frame1.getContentPane().add(label);
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
        button.setBounds(285, 330, 117, 29);
        button.addActionListener(this);
        frame1.getContentPane().add(button);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame1.setSize(700, 400);
		frame1.setVisible(true);
			
		
		
		
	}
	
	public static void main(String[] args) {
		Cancel cancel1 = new Cancel();
		cancel1.go();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int selectedFromComboBox = orderedTickets.getSelectedIndex();
		System.out.println(selectedFromComboBox); //选择了的键的index
		chosenTicketNumber = Integer.valueOf(orderedTicketsIndicator.get(selectedFromComboBox));
		// so now we are about to delete the number of chosenTicketNumber using JDBC
		// SYNTAX : delete from tickinfo where TICK_NUM = 9
		try {
			Connection connDelete = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway?useUnicode=true&characterEncoding=utf-8","root","19930807");
			PreparedStatement psDel = connDelete.prepareStatement("delete from tickinfo where TICK_NUM = ?");
			psDel.setInt(1, chosenTicketNumber);
			psDel.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
