package it.polito.tdp.anagrammi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect
{
	private static final String jdbcURL = "jdbc:mariadb://127.0.0.1/dizionario?user=root&password=root";
	
	public static Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(jdbcURL);
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
