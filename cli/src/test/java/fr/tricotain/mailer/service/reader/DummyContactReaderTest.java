package fr.tricotain.mailer.service.reader;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import fr.tricotain.mailer.model.Contact;

public class DummyContactReaderTest {

	@Test
	public void testReadContact() {
		DummyContactReader reader = new DummyContactReader();
		List<Contact> contacts = reader.readContact();
		Assert.assertNotNull(contacts);
		Assert.assertTrue(contacts.size()>0);
	}

}
