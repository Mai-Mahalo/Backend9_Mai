package com.neotech.db.lesson06;

// Part-1

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;
/*
 * Connect to classicmodels database 
 * Execute a query to get all information of
 * customer with id 124
 * Get the resultset metadata and print the number of columns
 * Get all column names and store them in an arraylist. 
 * Print the Arraylist
 */

public class Homework {

	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/classicmodels";


	@Test
	public void rsMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		// create a statement object
		Statement st = conn.createStatement();

		// execute a query
		ResultSet rs = st.executeQuery("select * from customers where customerNumber=124");

		// we have the result set, lets get the metadata
		ResultSetMetaData rsMetaData = rs.getMetaData();

		// what can i get from the ResultSetMetaData?

		// i can get the number of columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("Number of columns: " + columnCount);

		// lets create an ArrayList to store the column names
		ArrayList<String> columnNames = new ArrayList<>();

		// i can get the types of the columns
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			columnNames.add(columnName);
		}

		// Print the ArrayList in the console
		System.out.println("Column Names: " + columnNames);

		// lets print the customer name and phone number
		rs.next(); // move the cursor to the first row
		System.out.println("Customer Name: " + rs.getString("customerName"));
		System.out.println("Phone Number: " + rs.getString("phone"));

		// close the resources
		rs.close();
		st.close();
		conn.close();

	}
}
