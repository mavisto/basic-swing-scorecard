package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GolfCourseHelper {

	private static final GolfCourseHelper INSTANCE = new GolfCourseHelper();

	public static GolfCourseHelper getInstance() {
		return INSTANCE;
	}

	private GolfCourseHelper() {
	}

	public List<GolfCourse> getAllGolfCourses() throws SQLException {
		List<GolfCourse> courses = new ArrayList<>();
		String sql = "SELECT * FROM golfcourse ORDER By courseId";
		try(Connection connection = DbHelper.getConnection()) {
			PreparedStatement psmt = connection.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				GolfCourse course = new GolfCourse();
				course.setCourseId(rs.getLong("courseId"));
				course.setName(rs.getString("name"));
				course.setAddress(rs.getString("address"));
				course.setPhone(rs.getString("phone"));
				courses.add(course);
			}
		}
		
		return courses;
	}
	
	public GolfCourse getGolfCourse(long courseId) throws SQLException {
		GolfCourse course = new GolfCourse();
		String sql = "SELECT * FROM golfcourse where courseId = " + courseId;
		try(Connection connection = DbHelper.getConnection()) {
			PreparedStatement psmt = connection.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				course.setCourseId(rs.getLong("courseId"));
				course.setName(rs.getString("name"));
				course.setAddress(rs.getString("address"));
				course.setPhone(rs.getString("phone"));
			}
		}
		
		return course;
	}

}
