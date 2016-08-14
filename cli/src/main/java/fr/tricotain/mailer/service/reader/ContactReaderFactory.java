package fr.tricotain.mailer.service.reader;

import java.io.IOException;

public class ContactReaderFactory {

	public  static ContactReader getContactReader(ContactReaderFormat format, String filename) throws IOException {
		
		switch (format) {
			case DUMMY:
				return new DummyContactReader();
				
			case ENFANT:
				return new CSVEnfantContactReader(filename);
				
			case FAMILLE:
				return new CSVFamilleContactReader(filename);
	
			default:
				return null;
		}
		
	}
	
}
