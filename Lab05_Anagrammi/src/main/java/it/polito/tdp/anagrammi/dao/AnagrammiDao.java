package it.polito.tdp.anagrammi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammiDao
{
	public boolean isMeaningful(String word)
	{	
		String sqlQuery = String.format("%s %s %s", 
											"SELECT nome",
											"FROM parola",
											"WHERE nome = ?");
		
		try
		{
			Connection dbConnection = DBConnect.getDBConnection();
			PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
			statement.setString(1, word);
			ResultSet result = statement.executeQuery();
			
			boolean thereIsTheWord = result.next();
			
			DBConnect.closeResources(result, statement, dbConnection);
			
			return thereIsTheWord;
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("Error: DAO proble in 'isMeaningful()'", sqle);
		}
	}
	
	public boolean existsPrefix(String prefix)
	{
		String sqlQuery = String.format("%s %s %s%s%s",
											"SELECT nome",
											"FROM parola",
											"WHERE nome LIKE '",prefix,"%'");
		try
		{
			Connection connection = DBConnect.getDBConnection();
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			ResultSet result = statement.executeQuery();
			
			boolean existsPrefix = result.next();
			
			DBConnect.closeResources(result, statement, connection);
			
			return existsPrefix;
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("Error: DAO proble in 'existsPrefix()'", sqle);
		}
		
	}
}
