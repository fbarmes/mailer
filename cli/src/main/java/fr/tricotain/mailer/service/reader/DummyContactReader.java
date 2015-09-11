package fr.tricotain.mailer.service.reader;

import java.util.ArrayList;
import java.util.List;

import fr.tricotain.mailer.model.Contact;

public class DummyContactReader implements ContactReader {

	private static List<Contact> contacts = new ArrayList<>();
	static {
		contacts.add(new Contact("Mireille", "Labeille", "mireille.labeille@example.com"));
		contacts.add(new Contact("Camille", "LACHENILLE", "camille.lachenille@example.com"));
		contacts.add(new Contact("Johathan", "LE GOELAND", "jonathan.legoelan@example.com"));		
	}
	
		
	public DummyContactReader() {
		super();		
	}
	
	@Override
	public List<Contact> readContact() {
		return contacts;
	}

}
