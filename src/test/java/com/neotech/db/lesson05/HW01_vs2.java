package com.neotech.db.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/*
* Homework 1: // Used Try-Catch
       
       Connect to classicmodels database
       Execute a query to get all information of customer with id 124
       Get the resultset metadata
       Print the number of columns
       Get all the column names and store them in an arraylist
       Print the Arraylist
*/

//HINT: You can copy from MetaDataDemo and modify the code.

// Back-end automation: Java Database Connectivity
// = Java based API provides connectivity between Java and database


// 1. Insert a dependency in pom.xml (only for the first time)
// 2. Declare database Username, password and URL provided by company
// = Start with public static String .... = "XXXXX"
// 3. "Add @Test" to use JUnit.
// 4. we must use "public void" NOT "public static void main..."
// 5. Create a connection: Connection XXXXX = DriverManger.getConnection + need to provide necessary information
// 6. Print out "Successfully connected!"
// 7. Select "Try Catch". (recommended if the connection is failed)
// 8. Execute SQL "Statement" to request sending commands. (connection.createStatement) in Try
// 9. Execute the SQL statement. (ResultSet, connectiong object = statement.executeQuery (query in My SQL) in Try
// 10. Get MetaData (ResultSetMetaData, rsMetaData = ResultSet. getMetaData in Try
// 11. Print number of columns (int, columnsCount = ResultSetMetaData.getColumnCount)
// 12. Print the number of columns

public class HW01_vs2 {
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/classicmodels";
	
	@Test
	public void rsMetaData() {
		// rs = Result Set
		// Try-catch would be better than throw exception in real situation.   
		// because it may not close the connection in the end.                
		try {
			Connection conn = DriverManager.getConnection(dbUsername, dbPassword, dbUrl);
			System.out.println("Successfully connected!");
		
			// Execute the sql statement to request sending commands.
			Statement st = conn.createStatement();
			
			// execute the sql statement 
			ResultSet rs = st.executeQuery("SELECT * FROM customers WHERE customerNumber = 124");
			
			// Get metadata
			ResultSetMetaData rsMetaData = rs.getMetaData();
			
			// get number of columns
			int columnCount = rsMetaData.getColumnCount();
			System.out.println("The number of columns is " + columnCount);
			
		
		} catch (SQLException e) {
			System.out.println("Connection is failed.");
			e.printStackTrace();
			
			
		}
		
	}
	
	
	

}
