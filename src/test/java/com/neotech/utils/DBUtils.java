package com.neotech.utils;

// SQL Lesson 06, Part-3

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

public class DBUtils {

	private static Connection conn; 
	// converted to instance variables coz it would be 2 "conn" below.
	private static ResultSet rs;
	private static Statement st;
	// st = statement

	public static void getConnection() { // = Method
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
					ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {
			System.out.println("Error while establishing connection to the database.");
			e.printStackTrace();
		}
	}

	public static List<Map<String, String>> storeDataFromDb(String query) { // method
		// Getting data from database (=DB)
		// copy and pasted from "StoringData", and modified.

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();

			List<Map<String, String>> toReturn = new ArrayList<>();
			Map<String, String> map;

			while (rs.next()) {
				map = new LinkedHashMap<>();

				for (int i = 1; i <= columnCount; i++) {
					String columnName = rsMetaData.getColumnName(i);
					String columnValue = rs.getString(i);
					map.put(columnName, columnValue);
				}

				toReturn.add(map);
			}

			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Map<String, String>> storeDataFromDb2(String query) { 
		// method for the 1st method of StoringDataUsingDBUtils class.
		// This is the way to omit write code for opening and closing the connection.
		getConnection();
		List<Map<String, String>> data = storeDataFromDb(query);
		closeConnection();
		return data;
	}

	public static void closeConnection() { // method
		try {
			if (conn != null) {
				conn.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			System.out.println("Error while closing the connection.");
			e.printStackTrace();
		}
	}

}
