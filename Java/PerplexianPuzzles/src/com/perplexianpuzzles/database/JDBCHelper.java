package com.perplexianpuzzles.database;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.HashMap;

/**
 * A JDBC specific database helper.
 */
public class JDBCHelper extends DBHelper {
	
	private Connection _dbConnection;
	
	// Constructors ----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 * 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public JDBCHelper() throws InstantiationException, IllegalAccessException {
		super();
		
		// Make a DB connection
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			_dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/perplexian", "card_admin", "perplexian");
		} catch (SQLException e) {	
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void finalize() {
		try {
			_dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Public methods --------------------------------------------------------------------------
	/**
	 * Returns a PreparedStatement object. Do this in here since it has to come from the connection.
	 * 
	 * @return PreparedStatement		A PreparedStatemetn from the current connection
	 */
	@Override
	public PreparedStatement getPreparedStatement(String query) {
		PreparedStatement ret_val = null;
		
		try {
			ret_val = _dbConnection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret_val;
	}

	/**
	 * Executes the given query string and returns a single value.  If the query happens
	 * to return multiple values, the first will be returned by this method.
	 * 
	 * @param query				The SQL query to execute
	 * @return String			The value as a string (assuming no blobs, etc...)
	 */
	@Override
	public String querySingleValue(String query) {
		String ret_val = null;
		
		try {
			Statement statement =  _dbConnection.createStatement();
			ResultSet values = statement.executeQuery(query);

			// Only get the first value returned
			if (values.next()) {
				ret_val = values.getObject(1).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret_val;
	}

	/**
	 * Executes the prepared statement and returns a single value.  If the query happens
	 * to return multiple values, the first will be returned by this method.
	 * 
	 * @param prepStatement		The prepared statement to execute
	 * @return String			The value as a string (assuming no blobs, etc...)
	 */
	@Override
	public String querySingleValue(PreparedStatement prepStatement) {
		String ret_val = null;
		
		try {
			ResultSet values = prepStatement.executeQuery();

			// Only get the first value returned
			if (values.next()) {
				ret_val = values.getObject(1).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret_val;
	}

	@Override
	public HashMap<String, String> queryMultValue(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> queryMultValue(
			PreparedStatement prepStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String executeSproc(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
