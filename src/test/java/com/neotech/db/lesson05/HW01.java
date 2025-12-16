package com.neotech.db.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

/*
 * Homework 1: 
        
        Connect to classicmodels database
        Execute a query to get all information of customer with id 124
        Get the resultset metadata
        Print the number of columns
        Get all the column names and store them in an arraylist
        Print the Arraylist
 */

// HINT: You can copy from MetaDataDemo and modify the code.

public class HW01 {
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/classicmodels";
	
	@Test
	// When we use "JUnit" = @Test, we must use "public void" NOT "public static void main..."
	public void rsMetaData() throws SQLException { //= connectiong object
		// Try-catch would be better than throw exception in real situation.
		// because it may not close the connection in the end.
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		System.out.println("Successfully connected to the server.");
		
		// Execute the sql statement to request sending commands.
		Statement st = conn.createStatement();
		
		// execute the sql statement
		ResultSet rs = st.executeQuery("select * from customers where customerNumber = 124");
		
		// Get matadata
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		// Print the number of columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("Number of the columns are " + columnCount);
		
		// Get all the column names and store them in an arraylist
		ArrayList <String> columnNames = new ArrayList<>();
		
		// Through Lesson 6, part-1 video
		// I can get the types of the columns (through Lesson 6, part-1 video)
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			columnNames.add(columnName);
		}
		
		// Print the ArrayList in the console
		System.out.println("Column Names: " + columnNames);
		
		// lets print the customer name and phone number
		rs.next(); // move the cursor to the first row
		System.out.println("Customer Name: " + rs.getString("customerName"));
		System.out.println("Phone number: " + rs.getString("phone")); //"Phone" is ok, NOT case sensitive
		
		
		// close the resources (rs, st, and conn)
		conn.close();
		st.close();
		rs.close();	
	}
	
	

}

