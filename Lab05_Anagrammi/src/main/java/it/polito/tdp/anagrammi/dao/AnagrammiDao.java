package it.polito.tdp.anagrammi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammiDao
{
	private String sqlQuery = String.format("%s %s %s", 
												"SELECT nome",
												"FROM parola",
												"WHERE nome = ?");
	
	public boolean isMeaningful(String word)
	{		
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
			sqle.printStackTrace();
			throw new RuntimeException("Error: DAO problem", sqle);
		}
	}
}
