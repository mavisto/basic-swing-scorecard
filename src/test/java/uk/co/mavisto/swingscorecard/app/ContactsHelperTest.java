package uk.co.mavisto.swingscorecard.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.co.mavisto.swingscorecard.app.Contact;
import uk.co.mavisto.swingscorecard.app.ContactsHelper;
import uk.co.mavisto.swingscorecard.app.DbHelper;

public class ContactsHelperTest {
	
	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("TRUNCATE TABLE contacts");
			stmt.execute("ALTER TABLE contacts ALTER COLUMN id restart with 1");
		}
	}
	
	@Test
	public void testLoad() throws SQLException {
		List<Contact> contacts = ContactsHelper.getInstance().getContacts();
		Assert.assertNotNull(contacts);
		Assert.assertTrue(contacts.isEmpty());
		
		
		try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Dave Smith', 'dave.smith@sky.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Kay Smith', 'kay.smith@sky.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Caroline Smith', 'caz.smith@sky.com')");
			
			
			contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertNotNull(contacts);
			Assert.assertEquals(3,contacts.size());
			
			Contact contact = contacts.get(0);
			Assert.assertNotNull(contact);
			Assert.assertEquals(1L,contact.getId());
			Assert.assertEquals("Dave Smith",contact.getName());
			Assert.assertEquals("dave.smith@sky.com",contact.getContacts());
			
			contact = contacts.get(2);
			Assert.assertNotNull(contact);
			Assert.assertEquals(3L,contact.getId());
			Assert.assertEquals("Caroline Smith",contact.getName());
			Assert.assertEquals("caz.smith@sky.com",contact.getContacts());
		}
	}

}
