package fr.tricotain.mailer.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.tricotain.mailer.model.BusinessException;
import fr.tricotain.mailer.model.Config;
import fr.tricotain.mailer.model.Contact;
import fr.tricotain.mailer.model.MailMessage;
import fr.tricotain.mailer.service.mail.MailReport;
import fr.tricotain.mailer.service.mail.MailSender;
import fr.tricotain.mailer.service.reader.ContactReader;
import fr.tricotain.mailer.service.reader.ContactReaderFactory;
import fr.tricotain.mailer.service.reader.ContactReaderFormat;

public class Mailer {
	public static final Logger LOGGER = Logger.getLogger(Mailer.class);

	String contactFileName;
	String mailMessageSubject;
	String mailMessageBody;
	
	public Mailer() {
		super();
		this.contactFileName = Config.getInstance().getCsvFileName();
		this.mailMessageSubject = Config.getInstance().getMailSubject();
		this.mailMessageBody = Config.getInstance().getMailBody();
		
		
	}
	 
	
	public void run() {
		LOGGER.info("Start");
		
		try {
			//--- display config
			Config.getInstance().echoStatus();
			
			//--  read contacts
			String filename = Config.getInstance().getCsvFileName();
			String fileFormat = Config.getInstance().getCsvFileFormat();
			
			ContactReader reader = ContactReaderFactory.getContactReader(ContactReaderFormat.fromString(fileFormat), filename);
			
			if(reader == null) {
				throw new BusinessException("FileReader is null, bad format ("+fileFormat+") or name ("+filename+")");
			}
			
			List<Contact> contacts = reader.readContact();
			
			//-- Compose message
			MailMessage mailMessage  = new MailMessage(Config.getInstance().getMailFrom(), Config.getInstance().getMailSubject(), Config.getInstance().getMailBody());
			
			
			//--- send mails
			MailSender sender = new MailSender();
			List<MailReport> mailReports  = sender.sendMail(contacts, mailMessage);
			
			
			//-- display reports
			LOGGER.info("Report");
			for(MailReport report : mailReports) {
				LOGGER.info(report);
			}
		} catch (IOException e) {
			LOGGER.error("ERROR");
			LOGGER.error(e);
		} catch (BusinessException e) {
			LOGGER.error("ERROR");
			LOGGER.error(e);
		}
		
		
		LOGGER.info("Stop");

		
	}
	
}
