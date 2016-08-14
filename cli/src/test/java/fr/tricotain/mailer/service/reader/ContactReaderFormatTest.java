package fr.tricotain.mailer.service.reader;

import junit.framework.Assert;

import org.junit.Test;

public class ContactReaderFormatTest {

	@Test
	public void testFromString() {
		
		Assert.assertEquals(ContactReaderFormat.DUMMY, ContactReaderFormat.fromString("dummy") );
		Assert.assertEquals(ContactReaderFormat.DUMMY, ContactReaderFormat.fromString("duMMy") );
		
		Assert.assertEquals(ContactReaderFormat.ENFANT, ContactReaderFormat.fromString("enfant") );
		Assert.assertEquals(ContactReaderFormat.ENFANT, ContactReaderFormat.fromString("ENFANt") );
		
		Assert.assertEquals(ContactReaderFormat.FAMILLE, ContactReaderFormat.fromString("famille") );
		Assert.assertEquals(ContactReaderFormat.FAMILLE, ContactReaderFormat.fromString("fAmIllE") );
		
	}
	
}

