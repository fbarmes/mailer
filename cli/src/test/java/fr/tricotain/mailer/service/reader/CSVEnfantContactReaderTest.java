package fr.tricotain.mailer.service.reader;

import java.io.File;
import java.util.List;

import org.junit.Test;

import fr.tricotain.mailer.model.Contact;

import junit.framework.Assert;

public class CSVEnfantContactReaderTest {

    private final static String FILENAME = "src/test/resources/contact-list-simple.csv";
	
    @Test
	public void readContactTest() throws Exception {
		
    	File file = new File(FILENAME);
		CSVEnfantContactReader  contactReader = new CSVEnfantContactReader(file);
		Assert.assertNotNull(contactReader);
		
		List<Contact> contacts = contactReader.readContact();
		Assert.assertNotNull(contacts);
		Assert.assertTrue(contacts.size()>0);
	}
	
}
