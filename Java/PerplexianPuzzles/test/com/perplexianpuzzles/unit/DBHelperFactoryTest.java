package com.perplexianpuzzles.unit;

import static org.junit.Assert.*;
import org.junit.Test;

import com.perplexianpuzzles.database.DBHelper;
import com.perplexianpuzzles.database.DBHelperFactory;

public class DBHelperFactoryTest {

	private DBHelper _DBHelper = null;
	
	@Test
	public void testGetInstance() {
		
		// Make sure we always get the same instance (otherwise, not a Singleton)
		_DBHelper = DBHelperFactory.getInstance(DBHelperFactory.Helpers.JDBC);
		DBHelper DBHelper2 = DBHelperFactory.getInstance(DBHelperFactory.Helpers.JDBC);
		assertSame(_DBHelper, DBHelper2);
	}

}
