package com.academy.architecture.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBAccess implements DAOConstants {
	private static Connection conn;
	private static DataSource dataSource;
	
	public synchronized static Connection getConnection() 
			throws SQLException, ClassNotFoundException, NamingException {
		InitialContext context = new InitialContext();
		dataSource = (DataSource)context.lookup("java:/OracleDS");
		conn = dataSource.getConnection();
		return conn;
	}
		
	public static void closeConnection() throws SQLException {
		if(conn != null)
			conn.close();
	}
}
