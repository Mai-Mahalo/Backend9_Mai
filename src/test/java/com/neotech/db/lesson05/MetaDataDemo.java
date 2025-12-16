package com.neotech.db.lesson05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MetaDataDemo {

	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/classicmodels";

	@Test
	// @Test = main method
	// if we can't import "Test", add cucumber and JUnit to pom.xml
	public void dbMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		// import "java.sql.connection"
		// throw exception

		// Get the metadata of the database
		// Get necessary information (Driver name and version) from the server.
		DatabaseMetaData dbMetaData = conn.getMetaData();

		String driverName = dbMetaData.getDriverName(); 
		// get a driver version, it is shown "session" on the bottom left in MySQL Workbench
		// Eg: MariaDB Server (10.3.32-MariaDB)
		System.out.println(driverName);

		String dbVersion = dbMetaData.getDatabaseProductVersion();
		System.out.println(dbVersion);

		conn.close();
	}
	
	// Data = names of "SQL", "Oracle" and  etc.
	// result set meta data = How many columns, names of columns "BookName", "Bookid" and etc.

	@Test
	public void rsMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		// create a statement object
		Statement st = conn.createStatement();

		// execute a query
		ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE employeeNumber > 1111;");
		// This is from MySQL Workbench. "SELECT * FROM employees WHERE employeeNumber > 1111;"
		// We want to execute "employeeNumber" columns from "employees" table.

		// we have the result set, lets get the metadata
		ResultSetMetaData rsMetaData = rs.getMetaData();

		// what can i get from the ResultSetMetaData?

		// i can get the number of columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("Number of columns: " + columnCount);

		// i can get the name of first column
		String firstColumnName = rsMetaData.getColumnName(1);
		System.out.println("First column name: " + firstColumnName);

		// i can get the name of the third column
		String thirdColumnName = rsMetaData.getColumnName(3);
		System.out.println("Third column name: " + thirdColumnName);

		// i can get the types of the columns
		// The following data is shown in "Object Info" in the bottom left in MuSQL Workbench.
		// Choose "employee" table, and check "Object Info".
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			String columnType = rsMetaData.getColumnTypeName(i);
			System.out.println("Column " + i + ": " + columnName + " - Type: " + columnType);
		}

		// close the resources
		rs.close();
		st.close();
		conn.close();

	}
}
