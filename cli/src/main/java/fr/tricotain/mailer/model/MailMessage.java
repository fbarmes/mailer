package fr.tricotain.mailer.model;

public class MailMessage {

	private String from; 
	private String subject;
	private String textContent;
	
	
	
	public MailMessage() {
		super();
	}

	
	
	public MailMessage(String from, String subject, String textContent) {
		super();
		this.from = from;
		this.subject = subject;
		this.textContent = textContent;
	}



	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
	
}
