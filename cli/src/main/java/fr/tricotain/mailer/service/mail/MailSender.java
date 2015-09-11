package fr.tricotain.mailer.service.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import fr.tricotain.mailer.model.Config;
import fr.tricotain.mailer.model.Contact;
import fr.tricotain.mailer.model.MailMessage;
import fr.tricotain.mailer.model.MessageStatus;

public class MailSender {

	private static final Logger LOGGER = Logger.getLogger(MailSender.class);
	
	private static final String SMTP_HOST = Config.getInstance().getSmtpHost();
	private static final String SMTP_PORT = Config.getInstance().getSmtpPort();
	
	public MailSender() {
		super();
	}
	

	public List<MailReport> sendMail(List<Contact> contacts, MailMessage mailMessage) {
		
		List<MailReport> mailReports  = new ArrayList<>();
		
		for(Contact contact : contacts) {
			
			//--- sleep option
			try {
				int delay = Config.getInstance().getDelayBeforeSend();
				if(delay > 0) {
					LOGGER.debug("sleeping " + delay + " s");
					Thread.sleep(delay*1000);
				}
			} catch (InterruptedException e) {
				LOGGER.warn(e.getMessage());
			}
			
			MailReport report = this.sendMail(contact, mailMessage);
			mailReports.add(report);
		}
		
		return mailReports;
		
	}
	
	
	
	protected MailReport sendMail(Contact contact, MailMessage mailMessage)  {

		MailReport mailReport = new MailReport();
		mailReport.setContact(contact);
		
		try {
			LOGGER.info("send message to "+contact);
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", SMTP_HOST);
			properties.setProperty("mail.smtp.port", SMTP_PORT);
			
			Session session = Session.getDefaultInstance(properties);
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(mailMessage.getFrom()));
			
			if(Config.getInstance().isTestMode()) {
				String testMailAdress = Config.getInstance().getTestMailAdress();
				LOGGER.debug("test mode : using " + testMailAdress);
				mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(testMailAdress));
			} else {
				mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(contact.getEmail()));
			}
			
			
			
			mimeMessage.setSubject(mailMessage.getSubject());
			mimeMessage.setText(mailMessage.getTextContent());
			
			//--- handle attachments
			String attachmentFilename = Config.getInstance().getMailAttachmentFilename();
			if(attachmentFilename != null) {
				File file = new File(attachmentFilename);
				
				BodyPart messageBodyPart = new MimeBodyPart();
		        messageBodyPart.setText(mailMessage.getTextContent());
		        Multipart multipart = new MimeMultipart();
		        multipart.addBodyPart(messageBodyPart);
		        messageBodyPart = new MimeBodyPart();

		        DataSource source = new FileDataSource(attachmentFilename);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(file.getName());

		        multipart.addBodyPart(messageBodyPart);

		        mimeMessage.setContent(multipart);
			}
			
			
			Transport.send(mimeMessage);
			LOGGER.debug("send done OK");
			mailReport.setStatus(MessageStatus.OK);			
		} catch (MessagingException e) {
			LOGGER.debug("send done ERROR " + e.getMessage());
			LOGGER.error("error while sending to " + contact + ", "+e.getMessage() );
			mailReport.setStatus(MessageStatus.ERROR);
		}
		
		
		return mailReport;
	}
	
	
	
	
}
