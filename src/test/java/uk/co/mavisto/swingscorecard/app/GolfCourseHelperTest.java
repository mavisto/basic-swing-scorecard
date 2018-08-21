package uk.co.mavisto.swingscorecard.app;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GolfCourseHelperTest {

	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("TRUNCATE TABLE golfholerecord");
			stmt.execute("DELETE FROM golfcoursedata");
			stmt.execute("DELETE FROM golfcourse");
			stmt.execute("ALTER TABLE golfcourse ALTER COLUMN courseId restart with 1");
			stmt.execute("ALTER TABLE golfcoursedata ALTER COLUMN courseDataId restart with 1");
			stmt.execute("ALTER TABLE golfholerecord ALTER COLUMN id restart with 1");
		}
	}
	
	@Test
	public void testGetAllGolfCourses() throws SQLException {
		List<GolfCourse> golfCourses = GolfCourseHelper.getInstance().getAllGolfCourses();
		Assert.assertNotNull(golfCourses);
		Assert.assertTrue(golfCourses.isEmpty());
		
		
		try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
			stmt.execute("INSERT INTO golfcourse ( name, address, phone) VALUES('Bentham', 'Somewhere', '01999 999 999')");
			stmt.execute("INSERT INTO golfcourse ( name, address, phone) VALUES('Beacon Park', 'Up Holland', '01999 999 888')");
			stmt.execute("INSERT INTO golfcourse ( name, address, phone) VALUES('Royal Norwich', 'Norfolk', '01999 999 777')");
			
			
			golfCourses = GolfCourseHelper.getInstance().getAllGolfCourses();
			Assert.assertNotNull(golfCourses);
			Assert.assertEquals(3,golfCourses.size());
			
			GolfCourse golfCourse = golfCourses.get(0);
			Assert.assertNotNull(golfCourse);
			Assert.assertEquals(1L,golfCourse.getCourseId());
			Assert.assertEquals("Bentham",golfCourse.getName());
			Assert.assertEquals("Somewhere", golfCourse.getAddress());
			
			golfCourse = golfCourses.get(2);
			Assert.assertNotNull(golfCourse);
			Assert.assertEquals(3L,golfCourse.getCourseId());
			Assert.assertEquals("Royal Norwich",golfCourse.getName());
			Assert.assertEquals("Norfolk", golfCourse.getAddress());
		}
	}
	
	@Test
	public void testGetGolfCoursewithId( ) throws SQLException {
		GolfCourse golfCourse = GolfCourseHelper.getInstance().getGolfCourse(2L);
		Assert.assertNotNull(golfCourse);
				
		
		try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
			stmt.execute("INSERT INTO golfcourse ( name, address, phone) VALUES('Bentham', 'Somewhere', '01999 999 999')");
			stmt.execute("INSERT INTO golfcourse ( name, address, phone) VALUES('Beacon Park', 'Up Holland', '01999 999 888')");
			stmt.execute("INSERT INTO golfcourse ( name, address, phone) VALUES('Royal Norwich', 'Norfolk', '01999 999 777')");
			
			golfCourse = GolfCourseHelper.getInstance().getGolfCourse(2L);
			
			Assert.assertNotNull(golfCourse);
			Assert.assertEquals(2L,golfCourse.getCourseId());
			Assert.assertEquals("Beacon Park",golfCourse.getName());
			Assert.assertEquals("Up Holland", golfCourse.getAddress());
		}
	}

}
