package uk.co.mavisto.swingscorecard.app;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HoleRecordTest {
	
	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();
	}

	@After
	public void close() {
		DbHelper.getInstance().close();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
