package com.neotech.db.lesson05;

// Part 3

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1 {
	
	// To connect the database
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";

	// DB URL format: jdbc:mysql://<host>:<port>/<database>
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/LibraryMgmt";

	public static void main(String[] args) throws SQLException {

		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		// getConnection -> Need to do SQLException just in case if the server is not working.

		System.out.println("Connection established successfully!");

		// create a sql statement
		Statement st = conn.createStatement();
		// statement = object, import "java.sql.Statement"

		// execute the sql statement
		ResultSet rs = st.executeQuery("Select * from book");
		// "Select * from book" is from MySQL Workbench to show all books.
		// "ResutlSet" to show the result we see in SQL Workbench.

		System.out.println("------------------");
		System.out.println(rs.toString());

		// how can i iterate through the result set?
		rs.next(); // move the cursor to the first row
		String bookName1 = rs.getString("BookName");
		System.out.println(bookName1);

		rs.next(); // move the cursor to the second row
		String bookName2 = rs.getString("BookName");
		System.out.println(bookName2);

		// lets iterate through all the rows
		System.out.println("Iterating through all the rows:");

		while (rs.next()) {
			// move the cursor to the next row
			// get the value of the column "BookName"
			String bookName = rs.getString("BookName");
			System.out.println(bookName);
		}

		// close the connection
		rs.close();
		st.close();
		conn.close();

	}

}
