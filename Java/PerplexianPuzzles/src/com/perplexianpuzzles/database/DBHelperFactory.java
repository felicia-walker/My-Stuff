package com.perplexianpuzzles.database;

/**
 * Returns a singleton instance of a specified database helper class.  The helpers were
 * implemented in this factory since you can't really do full singletons using inheritance.
 * The singleton instantiation functionality is handled by this factory instead of in the
 * helpers themselves.
 */
public class DBHelperFactory {

	public enum Helpers {JDBC};			// Add Torque in the future
	
	// Store instances in an array, so each type is a singleton
	private static final DBHelper[] _instances = new DBHelper[Helpers.values().length];
	
	// Initialization block ---------------------------------------------------
	static {
		
		// Loop based initializer to make it easier when other Helpers are added
		for (Helpers curHelper : Helpers.values()) {
			_instances[curHelper.ordinal()] = null;
		}
	}
	
	// Constructors -----------------------------------------------------------
	/**
	 * Default constructor is empty since this only has class methods.
	 */
	DBHelperFactory () {
	}
	
	// Public methods ---------------------------------------------------------
	/**
	 * Return in instance of the specified database helper.
	 * 
	 * @param type			The type of helper to get. Valid values are listed in the
	 * 						Helpers enum.
	 * @return DBHelper		The helper instance
	 */
	public static DBHelper getInstance(Helpers type) {
		DBHelper ret_val = null;
		
		switch (type) {
			case JDBC:
				int tmpIndex = Helpers.JDBC.ordinal();
				if (_instances[tmpIndex] != null) {
					try {
						_instances[tmpIndex] = new JDBCHelper();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			
				ret_val = _instances[tmpIndex];
				break;
				
			default:
				
				// Should never get here
				assert false : "Invalid DBHelper type.";
		}
		
		return ret_val;
	}
}
