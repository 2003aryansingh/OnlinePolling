package org.aryan.JavaOnlinePoll.service;

import java.sql.*;


public class ConnectDB {

	public Connection createConnection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OnlinePolling","postgres","a@1234567890");
			
			if(conn != null) {
				System.out.println("Connection Established");
			} else {
				System.out.println("Connection Failed");
				conn = null;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	
	
}
