package com;

/**
 * Caution: MODIFYING OF THESE IMPORTION MAY CAUSE EXCEPTION
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	/**
	 * Connection: gets the connection between java and databases
	 * Statement: respond for executing SQL statements
	 * ResultSet: Stores the outcomes from SELECT sentences
	 * Importing packages: control + shift + o
	 * 
	 * 
	 * gets connection
	 * @throws SQLException */
	
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","19930807");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		 
		return conn;
		
	} 
	
	
	
	public static void main(String[] args) throws Exception{
		//System.out.println(getConnection());
		
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		
		//这里没有结果集还是不能输出的一般导入 java.sql
		ResultSet rs = st.executeQuery("select * from passinfo; ");
		while(rs.next()){
			/**
			 * points to the next array (downwards)
			 * if not null returns true, otherwise returns false
			 */
			//System.out.println("Name: "+ rs.getString("pname"));
			System.out.println(rs.getString("USER_ID"));
			//System.out.println(rs.getInt("TICKETNO"));
			
			
			
		}
		
	}
	

}
