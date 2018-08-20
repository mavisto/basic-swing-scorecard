package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoleRecordHelper {

	private static final HoleRecordHelper INSTANCE = new HoleRecordHelper();

	public static HoleRecordHelper getInstance() {
		return INSTANCE;
	}

	private HoleRecordHelper() {
	}

	public List<HoleRecord> getHoleRecords() throws SQLException {
		List<HoleRecord> holeRecords = new ArrayList<>();
		String sql = "SELECT * FROM golfholerecord ORDER By holeNumber";
		try(Connection connection = DbHelper.getConnection()) {
			PreparedStatement psmt = connection.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				HoleRecord holeRecord = new HoleRecord();
				holeRecord.setId(rs.getLong("id"));
				holeRecord.setHoleNumber(rs.getLong("holeNumber"));
				holeRecord.setHoleYardage(rs.getLong("holeYardage"));
				holeRecord.setHolePar(rs.getLong("holePar"));
				holeRecord.setHoleSI(rs.getLong("holeSI"));
				holeRecords.add(holeRecord);
			}
		}
		
		return holeRecords;
	}

	public List<HoleRecord> getHoleRecordsForCourse(Long courseId, Long courseDataId) throws SQLException {
			List<HoleRecord> holeRecords = new ArrayList<>();
			String sql = "SELECT * FROM golfholerecord WHERE courseId = ?  and courseDataId = ? ORDER By holeNumber";
			try(Connection connection = DbHelper.getConnection()) {
				PreparedStatement psmt = connection.prepareStatement(sql);
				psmt.setLong(1, courseId);
				psmt.setLong(2, courseDataId);
				psmt.execute();
				ResultSet rs = psmt.getResultSet();
				
				while(rs.next()) {
					HoleRecord holeRecord = new HoleRecord();
					holeRecord.setId(rs.getLong("id"));
					holeRecord.setHoleNumber(rs.getLong("holeNumber"));
					holeRecord.setHoleYardage(rs.getLong("holeYardage"));
					holeRecord.setHolePar(rs.getLong("holePar"));
					holeRecord.setHoleSI(rs.getLong("holeSI"));
					holeRecords.add(holeRecord);
				}
			}
			
			return holeRecords;
	}

}
