package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseHelper {

	private static final CourseHelper INSTANCE = new CourseHelper();

	public static CourseHelper getInstance() {
		return INSTANCE;
	}

	private CourseHelper() {
	}

	public List<Contact> getContacts() throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		String sql = "SELECT * FROM golfcourse ORDER By courseId";
		try(Connection connection = DbHelper.getConnection()) {
			PreparedStatement psmt = connection.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getLong("id"));
				contact.setName(rs.getString("address"));
				contact.setContacts(rs.getString("phone"));
				contacts.add(contact);
			}
		}
		
		return contacts;
	}

}
