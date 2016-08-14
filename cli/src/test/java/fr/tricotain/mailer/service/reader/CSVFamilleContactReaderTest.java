package fr.tricotain.mailer.service.reader;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import fr.tricotain.mailer.model.Contact;

public class CSVFamilleContactReaderTest {

	private final static String FILENAME = "src/test/resources/contact-list-famille.csv";
	
	@Test
	public void testShortLines() throws Exception {
		String [] veryShortLine = new String[5];
		String [] shortLine = new String[14];
		String [] exactLine = new String[15];
		String [] longLine = new String[16];
		String [] veryLongLine = new String[20];

		
		CSVFamilleContactReader contactReader = new CSVFamilleContactReader(FILENAME);
		Assert.assertNotNull(contactReader);
		
		Assert.assertTrue(contactReader.isShortLine(veryShortLine));
		Assert.assertTrue(contactReader.isShortLine(shortLine));
		Assert.assertFalse(contactReader.isShortLine(exactLine));
		Assert.assertFalse(contactReader.isShortLine(longLine));
		Assert.assertFalse(contactReader.isShortLine(veryLongLine));
		
	}
	
    @Test
	public void readContactTest() throws Exception {
		
    	File file = new File(FILENAME);
		ContactReader  contactReader = new CSVFamilleContactReader(file);
		Assert.assertNotNull(contactReader);
		
		List<Contact> contacts = contactReader.readContact();
		Assert.assertNotNull(contacts);
		Assert.assertTrue(contacts.size()>0);
	}

	
}
