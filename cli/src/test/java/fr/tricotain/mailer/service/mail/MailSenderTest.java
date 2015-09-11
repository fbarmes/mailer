package fr.tricotain.mailer.service.mail;



import java.util.List;

import org.junit.Test;

import fr.tricotain.mailer.model.Contact;
import fr.tricotain.mailer.model.MailMessage;
import fr.tricotain.mailer.service.reader.ContactReader;
import fr.tricotain.mailer.service.reader.DummyContactReader;

public class MailSenderTest {

	@Test
	public void testSendMailMultiple() throws Exception {

		ContactReader contactReader = new DummyContactReader();
		List<Contact> contacts = contactReader.readContact();
		
		MailMessage message  = new MailMessage("sender@example.com","Dummy subject","Lorem ipsum dolor sit amet");
		
		
		MailSender sender = new MailSender();
		sender.sendMail(contacts, message);
	}
	
	
	
	@Test
	public void testSendMail() throws Exception {

		Contact contact  = new Contact("Mireille", "LABEILLE", "mireille.labeillee@example.com");
		MailMessage message  = new MailMessage("sender@example.com","Dummy Subject","Lorem ipsum dolor sit amet");
		MailSender sender = new MailSender();
		sender.sendMail(contact, message);
	}

}
