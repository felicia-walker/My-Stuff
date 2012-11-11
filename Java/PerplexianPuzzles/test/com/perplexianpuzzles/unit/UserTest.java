package com.perplexianpuzzles.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.perplexianpuzzles.User;

public class UserTest {

	private User testUser;
	
	@Before
	public void setUp() throws Exception {
		testUser = new User();
	}

	@Test
	public void testAlias() {
		testUser.setAlias("xyzzy");
		String alias = testUser.getAlias();
		assertEquals("xyzzy", alias);
	}

	@Test
	public void testLocation() {
		testUser.setLocation("Seattle");
		String location = testUser.getLocation();
		assertEquals("Seattle", location);
	}

}
