/**
 * 
 */
package com.perplexianpuzzles.database;

import java.sql.PreparedStatement;
import java.util.HashMap;

/**
 * @author Scott Walker
 *
 */
public abstract class DBHelper {
	  
	// This included for singleton purposes.
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}
	
	// Need a way to get a prepared statement object
	public abstract PreparedStatement getPreparedStatement(String query);

	// Queries that return a single value
	public abstract String querySingleValue(String query);
	public abstract String querySingleValue(PreparedStatement prepStatement);
	
	// Queries that return multiple values.  The hashmap has keys for each column.
	public abstract HashMap<String, String> queryMultValue(String query);
	public abstract HashMap<String, String> queryMultValue(PreparedStatement prepStatement);
	
	// Execute a stored procedure.  May or may not return a value.
	public abstract String executeSproc(String query);
}
