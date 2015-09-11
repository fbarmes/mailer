package fr.tricotain.mailer.service.mail;

import fr.tricotain.mailer.model.Contact;
import fr.tricotain.mailer.model.MessageStatus;

public class MailReport {

	private Contact contact;
	private MessageStatus  status;

	
	public MailReport() {
		super();
	}

	
	
	public Contact getContact() {
		return contact;
	}



	public void setContact(Contact contact) {
		this.contact = contact;
	}



	public MessageStatus getStatus() {
		return status;
	}



	public void setStatus(MessageStatus status) {
		this.status = status;
	}



	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(contact.getLastname()).append(" ").append(contact.getFirstname());
		sb.append(" <"+contact.getEmail()+"> :");
		sb.append(status.getCode());
		
		return sb.toString();
	}
	
	
}
