/**
 * 
 */
package com.perplexianpuzzles;

/**
 * @author Scott Walker
 *
 */
public class User {

	private String _alias = "";
	private String _location = "";
	
	// Getters -----------------------------------------------------------------
	public String getAlias() {
		return _alias;
	}
	
	public String getLocation() {
		return _location;
	}
	
	// Setters -----------------------------------------------------------------
	public void setAlias(String alias) {
		this._alias = alias;
	}
	
	public void setLocation(String location) {
		this._location = location;
	}
}
