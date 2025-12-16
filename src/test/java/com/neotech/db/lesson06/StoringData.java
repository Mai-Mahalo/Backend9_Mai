package com.neotech.db.lesson06;

// Part-2

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

public class StoringData {

	@Test
	public void getAndStoreData() throws SQLException {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		Connection conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
				ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));

		Statement st = conn.createStatement();
		ResultSet rs = st
				.executeQuery("select employeeNumber,lastName, firstName, email, from employees limit 5;");

		List<Map<String, String>> employeeList = new ArrayList<>();
		Map<String, String> employeeData;

		while (rs.next()) {
			employeeData = new LinkedHashMap<>();

			employeeData.put("Employee Number", rs.getString("employeeNumber"));
			employeeData.put("Last Name", rs.getString("lastName"));
			employeeData.put("First Name", rs.getString("firstName"));
			employeeData.put("Email", rs.getString("email"));

			employeeList.add(employeeData);
		}

		System.out.println(employeeList);

		// Close resources
		rs.close();
		st.close();
		conn.close();
	}

	@Test
	public void getAndStoreDataEnhanced() throws SQLException {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		Connection conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
				ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));

		Statement st = conn.createStatement();
		ResultSet rs = st
				.executeQuery("select employeeNumber,lastName, firstName, email from employees limit 5;");

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();

		List<Map<String, String>> employeeList = new ArrayList<>();
		Map<String, String> employeeData;

		while (rs.next()) {
			employeeData = new LinkedHashMap<>();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsMetaData.getColumnName(i);
				String columnValue = rs.getString(i);
				employeeData.put(columnName, columnValue);
				
				// This code above is more dynamic and improved from the one below.
				
//				while (rs.next()) {
//					employeeData = new LinkedHashMap<>();
//
//					employeeData.put("Employee Number", rs.getString("employeeNumber"));
//					employeeData.put("Last Name", rs.getString("lastName"));
//					employeeData.put("First Name", rs.getString("firstName"));
//					employeeData.put("Email", rs.getString("email"));
//
//					employeeList.add(employeeData);

			}

			employeeList.add(employeeData);
		}

		// Print the employee data
		System.out.println("-------------------");
		System.out.println("printing employee data with enhanced method:");
		System.out.println(employeeList);

		// Close resources
		rs.close();
		st.close();
		conn.close();
	}
}
