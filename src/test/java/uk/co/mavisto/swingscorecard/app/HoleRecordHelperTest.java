package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.co.mavisto.swingscorecard.app.DbHelper;

public class HoleRecordHelperTest {
	
	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("TRUNCATE TABLE golfholerecord");
			stmt.execute("ALTER TABLE golfholerecord ALTER COLUMN id restart with 1");
		}
	}
	
	@Test
	public void testLoad() throws SQLException {
		List<HoleRecord> holeRecords = HoleRecordHelper.getInstance().getHoleRecords();
		Assert.assertNotNull(holeRecords);
		Assert.assertTrue(holeRecords.isEmpty());
		
		
		try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(1,2,1,369,4,16)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(1,2,2,549,5,12)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(1,2,3,165,3,9)");
			
			
			holeRecords = HoleRecordHelper.getInstance().getHoleRecords();
			Assert.assertNotNull(holeRecords);
			Assert.assertEquals(3,holeRecords.size());
			
			HoleRecord holeRecord = holeRecords.get(0);
			Assert.assertNotNull(holeRecord);
			Assert.assertEquals(1L,holeRecord.getId());
			Assert.assertEquals(369,holeRecord.getHoleYardage());
			Assert.assertEquals(16, holeRecord.getHoleSI());
			
			holeRecord = holeRecords.get(2);
			Assert.assertNotNull(holeRecord);
			Assert.assertEquals(3L,holeRecord.getId());
			Assert.assertEquals(165,holeRecord.getHoleYardage());
			Assert.assertEquals(9, holeRecord.getHoleSI());
		}
	}
	
	@Test
	public void getHoleRecordsForCourse( ) throws SQLException {
		List<HoleRecord> holeRecords = HoleRecordHelper.getInstance().getHoleRecords();
		Assert.assertNotNull(holeRecords);
		Assert.assertTrue(holeRecords.isEmpty());
				
		
		try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(1,2,1,369,4,16)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(1,2,2,549,5,12)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(1,2,3,165,3,9)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(2,3,1,123,3,12)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(2,3,2,439,4,8)");
			stmt.execute("INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(2,3,3,565,5,1)");
			
			holeRecords = HoleRecordHelper.getInstance().getHoleRecordsForCourse(2L, 3L);
			Assert.assertNotNull(holeRecords);
			Assert.assertEquals(3,holeRecords.size());
			
			HoleRecord holeRecord = holeRecords.get(0);
			Assert.assertNotNull(holeRecord);
			Assert.assertEquals(4L,holeRecord.getId());
			Assert.assertEquals(123,holeRecord.getHoleYardage());
			Assert.assertEquals(12, holeRecord.getHoleSI());
			
			holeRecord = holeRecords.get(2);
			Assert.assertNotNull(holeRecord);
			Assert.assertEquals(6L,holeRecord.getId());
			Assert.assertEquals(565,holeRecord.getHoleYardage());
			Assert.assertEquals(1, holeRecord.getHoleSI());
		}
	}

}
