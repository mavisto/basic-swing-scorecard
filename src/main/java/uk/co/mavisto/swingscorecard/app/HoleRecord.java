package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoleRecord {
	private long id = -1;
	private long courseId;
	private long courseDataId;
	private long holeNumber;
	private long holeYardage;
	private long holePar;
	private long holeSI;
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getCourseDataId() {
		return courseDataId;
	}
	public void setCourseDataId(long courseDataId) {
		this.courseDataId = courseDataId;
	}
	public long getHoleNumber() {
		return holeNumber;
	}
	public void setHoleNumber(long holeNumber) {
		this.holeNumber = holeNumber;
	}
	public long getHoleYardage() {
		return holeYardage;
	}
	public void setHoleYardage(long holeYardage) {
		this.holeYardage = holeYardage;
	}
	public long getHolePar() {
		return holePar;
	}
	public void setHolePar(long holePar) {
		this.holePar = holePar;
	}
	public long getHoleSI() {
		return holeSI;
	}
	public void setHoleSI(long holeSI) {
		this.holeSI = holeSI;
	}
	
	public void save() throws SQLException {

		try (Connection connection = DbHelper.getConnection()) {
			if (id == -1) {

				final String sql = "INSERT INTO golfholerecord (courseId, courseDataId, holeNumber, holeYardage, holePar, holeSI) VALUES(?,?,?,?,?,?)";
				try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					pstmt.setLong(1, courseId);
					pstmt.setLong(2, courseDataId);
					pstmt.setLong(3, holeNumber);
					pstmt.setLong(4, holeYardage);
					pstmt.setLong(5, holePar);
					pstmt.setLong(6, holeSI);			
					pstmt.execute();

					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						rs.next();
						id = rs.getLong(1);
					}
				}

			} else {
				final String sql = "UPDATE contacts SET courseId = ?, courseDataId = ?, holeNumber = ?, holeYardage = ?, holePar = ?, holeSI = ? WHERE id = ?";
				try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
					pstmt.setLong(1, courseId);
					pstmt.setLong(2, courseDataId);
					pstmt.setLong(3, holeNumber);
					pstmt.setLong(4, holeYardage);
					pstmt.setLong(5, holePar);
					pstmt.setLong(6, holeSI);
					pstmt.setLong(7, id);
					pstmt.execute();
				}
			}
		}
	}

}
