package it.polito.tdp.anagrammi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect
{
	private static final String jdbcURL = "jdbc:mariadb://127.0.0.1/dizionario";
	private static final String user = "root";
	private static final String password = "root";
	private static HikariDataSource dataSource = null;

	static //dataSource initialization
	{
		dataSource = new HikariDataSource();
		
		dataSource.setJdbcUrl(jdbcURL);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		dataSource.setMaximumPoolSize(5);
	}
	
	
	public static Connection getDBConnection()
	{		
		try
		{
			return dataSource.getConnection();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL Error in getDBConnection()", sqle);
		}
	}
	
	public static Connection oldGetDBConnection()
	{		
		try
		{
			return DriverManager.getConnection(jdbcURL, user, password);
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL Error in oldGetDBConnection()", sqle);
		}
	}
	
	public static void closeResources(AutoCloseable... resources) throws SQLException 
	{
		try
		{
			for(AutoCloseable resource : resources)
				resource.close();
		}
		catch(Exception e)
		{
			throw new SQLException("Closing resources failed");
		}
	}
}
