package fr.tricotain.mailer.model;

import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {

	private static final Logger LOGGER = Logger.getLogger(Config.class);
	
	private static Config instance;
	
	private boolean testMode = true;
	private String testMailAdress = "receiver@example.com";
	
	private String smtpHost = "localhost";
	private String smtpPort = "2525";
	private int delayBeforeSend = 1;
	
	private String mailFrom = "sender@example.com";
	private String mailSubject = "";
	private String mailBody = ""; 
	private String mailMessageFileEncoding = "ISO-8859-1";
	private String mailAttachmentFilename;
	
	private String csvFileName = "";
	
	
	private String csvFileEncoding = "ISO-8859-1";
	private char csvFileCharacterSeparator = ';';
	private char csvFileQuoteSeparator =  '"';
	private int csvFileIndexLastName = 0;
	private int csvFileIndexFirstName = 1;
	private int csvFileIndexEmail = 8;
	
	public synchronized static Config getInstance() {
		if(instance == null) {
			instance = new Config();
		}
		return instance;
	}
	
	
	public void echoStatus() {
		LOGGER.info("Configuration status");
		
		LOGGER.info("---");
		LOGGER.info("General Settings");
		LOGGER.info("test mode : " + this.testMode);
		
		LOGGER.info("---");
		LOGGER.info("Server Settings");
		LOGGER.info("SMTP host :" + this.smtpHost);
		LOGGER.info("SMTP port :" + this.smtpPort);
		
		
		LOGGER.info("---");
		LOGGER.info("Message settings");
		LOGGER.info("From :" + this.mailFrom);
		LOGGER.info("Subject :" + this.mailSubject);

		LOGGER.info("---");
		LOGGER.info("contacts");
		LOGGER.info("contact file :" + this.csvFileName);

		
	}
	
	public void setProperties(Properties props) {

		if(props.containsKey("test.mode")) {
			String value = props.getProperty("test.mode");
			this.testMode = Boolean.valueOf(value).booleanValue();
			
		}
		
		if(props.containsKey("smtp.host")) {
			this.smtpHost = props.getProperty("smtp.host");
		}

		if(props.containsKey("smtp.port")) {
			this.smtpPort = props.getProperty("smtp.port");
		}
		
		if(props.containsKey("mail.from")) {
			this.mailFrom = props.getProperty("mail.from");
		}
		
		if(props.containsKey("mail.subject")) {
			this.mailSubject = props.getProperty("mail.subject");
		}
		
	}
	
	
	
	
	private Config() {
		super();
	}


	public String getSmtpHost() {
		return smtpHost;
	}


	public String getSmtpPort() {
		return smtpPort;
	}


	public String getMailFrom() {
		return mailFrom;
	}



	public String getMailSubject() {
		return mailSubject;
	}


	public String getCsvFileEncoding() {
		return csvFileEncoding;
	}


	public char getCsvFileCharacterSeparator() {
		return csvFileCharacterSeparator;
	}


	public char getCsvFileQuoteSeparator() {
		return csvFileQuoteSeparator;
	}


	public int getCsvFileIndexLastName() {
		return csvFileIndexLastName;
	}


	public int getCsvFileIndexFirstName() {
		return csvFileIndexFirstName;
	}


	public int getCsvFileIndexEmail() {
		return csvFileIndexEmail;
	}


	public String getMailBody() {
		return mailBody;
	}
	
	


	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getCsvFileName() {
		return csvFileName;
	}
	
	


	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	public boolean isTestMode() {
		return testMode;
	}


	public String getTestMailAdress() {
		return testMailAdress;
	}


	public int getDelayBeforeSend() {
		return delayBeforeSend;
	}

	public String getMailMessageFileEncoding() {
		return mailMessageFileEncoding;
	}


	public static Logger getLogger() {
		return LOGGER;
	}


	public String getMailAttachmentFilename() {
		return mailAttachmentFilename;
	}


	public void setMailAttachmentFilename(String mailAttachmentFilename) {
		this.mailAttachmentFilename = mailAttachmentFilename;
	}

	
}
