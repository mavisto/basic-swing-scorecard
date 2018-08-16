package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Contact {

	private long id = -1;
	private String name;
	private String contacts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public void delete() throws SQLException {

		if (id != -1) {
			final String sql = "DELETE FROM contacts WHERE id = ?";
			try (Connection connection = DbHelper.getConnection();
					PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setLong(1, id);
				pstmt.execute();
				id = -1;
			}
		}

	}

	public void save() throws SQLException {

		try (Connection connection = DbHelper.getConnection()) {
			if (id == -1) {

				final String sql = "INSERT INTO contacts (name, contacts) VALUES(?,?)";
				try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					pstmt.setString(1, name);
					pstmt.setString(2, contacts);
					pstmt.execute();

					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						rs.next();
						id = rs.getLong(1);
					}
				}

			} else {
				final String sql = "UPDATE contacts SET name = ?, contacts = ? WHERE id = ?";
				try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
					pstmt.setString(1, name);
					pstmt.setString(2, contacts);
					pstmt.setLong(3, id);
					pstmt.execute();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder formatted = new StringBuilder();
		if(id == -1) {
			formatted.append("No Id ");
		} else {
			formatted.append("[").append(id).append("]");
		}
		
		if(name == null) {
			formatted.append("No Name ");
		} else {
			formatted.append(name);
		}
		return formatted.toString();
	}
}
