package com.perplexianpuzzles.unit;

import static org.junit.Assert.*;
import junit.framework.Assert;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.perplexianpuzzles.database.JDBCHelper;

public class JDBCHelperTest {

	private JDBCHelper _helper = null;
	
	@Before
	public void setUp() throws Exception {
		_helper = new JDBCHelper();
	}

	@Test
	public void testQuerySingleValueString() {
		String alias = _helper.querySingleValue("SELECT alias FROM tbl_user ORDER BY alias");
		Assert.assertEquals("minerva", alias);
	}

	@Test
	public void testQuerySingleValuePreparedStatement() {
		PreparedStatement countStatement = _helper.getPreparedStatement("SELECT alias FROM tbl_user WHERE rank = ?");
		
		try {
			countStatement.setInt(1, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String alias = _helper.querySingleValue(countStatement);
		Assert.assertEquals("volvox", alias);

		// Reuse the prepared statement to test it
		try {
			countStatement.setInt(1, 2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		alias = _helper.querySingleValue(countStatement);
		Assert.assertEquals("minerva", alias);
	}

	@Test
	public void testQueryMultValueString() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryMultValuePreparedStatement() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteSproc() {
		fail("Not yet implemented");
	}

	@Test
	public void testJDBCHelper() {
		fail("Not yet implemented");
	}

	@After
	public void tearDown() throws Exception {
		_helper = null;
	}
}
