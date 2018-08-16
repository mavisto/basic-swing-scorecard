package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.co.mavisto.swingscorecard.app.Contact;
import uk.co.mavisto.swingscorecard.app.ContactsHelper;
import uk.co.mavisto.swingscorecard.app.DbHelper;

public class ContactTest {

	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("TRUNCATE TABLE contacts");
			stmt.execute("ALTER TABLE contacts ALTER COLUMN id restart with 1");
		}
	}

	@After
	public void close() {
		DbHelper.getInstance().close();
	}
	
	@Test
	public void testDelete() throws SQLException {
		try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Dave Smith', 'dave.smith@sky.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Kay Smith', 'kay.smith@sky.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Caroline Smith', 'caz.smith@sky.com')");
			
			List<Contact> contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertEquals(3,contacts.size());
			
			final Contact contact = contacts.get(2);
			Assert.assertNotEquals(-1,contact.getId());
			contact.delete();
			Assert.assertEquals(-1,contact.getId());
			
			contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertEquals(2, contacts.size());
			Assert.assertEquals(1L, contacts.get(0).getId());
			Assert.assertEquals(1L, contacts.get(0).getId());
		}
	}

	@Test
	public void testSave() throws SQLException {
		Contact c = new Contact();
		c.setName("Dave Smith");
		c.setContacts("dsmavisti@gmail.com");
		Assert.assertEquals(-1L, c.getId());
		c.save();
		Assert.assertEquals(1L, c.getId());

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM contacts")) {
				Assert.assertTrue("Count shoud return one row", rs.next());
				Assert.assertEquals(1L, rs.getLong(1));
				Assert.assertFalse("Count should not reurn more than one row", rs.next());
			}

			try (ResultSet rs = stmt.executeQuery("SELECT * FROM contacts")) {
				Assert.assertTrue("Select should return one row", rs.next());
				Assert.assertEquals(1L, rs.getLong("id"));
				Assert.assertEquals("Dave Smith", rs.getString("name"));
				Assert.assertEquals("dsmavisti@gmail.com", rs.getString("contacts"));
				Assert.assertFalse("Select should not reurn more than one row", rs.next());
			}
		}
		
		c.setName("Smith Dave");
		c.save();
		
		Assert.assertEquals(1L, c.getId());
		Assert.assertEquals("Smith Dave", c.getName());
		Assert.assertEquals("dsmavisti@gmail.com", c.getContacts());
		
		
		final List<Contact> contacts = ContactsHelper.getInstance().getContacts();
		Assert.assertEquals(1,contacts.size());
		
		final Contact contact = contacts.get(0);
		Assert.assertEquals(1L,contact.getId());
		Assert.assertEquals("Smith Dave",contact.getName());
		Assert.assertEquals("dsmavisti@gmail.com",contact.getContacts());
	}
}
