package fr.tricotain.mailer.service.reader;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import fr.tricotain.mailer.model.Contact;

public class CSVEnfantContactReader extends CSVContactReader {

	private static final Logger LOGGER = Logger.getLogger(CSVEnfantContactReader.class);

	private static final String FILE_ENCODING = "ISO-8859-1";
	private static final char  CSV_CHAR_SEPARATOR = ';';
	private static final char CSV_QUOTE_SEPARATOR = '"';
	
	private static final int CSV_INDEX_LASTNAME = 0;
	private static final int CSV_INDEX_FIRSTNAME = 1;
	private static final int CSV_INDEX_EMAIL = 8;

	public CSVEnfantContactReader(String filename) throws IOException {
		super(filename);
	}
		
	public CSVEnfantContactReader(File file) throws IOException {
		super(file);
	}
	
	
	@Override
	protected String getCsvFileEncoding() {
		return FILE_ENCODING;
	}
	
	@Override
	protected char getCsvCharacterSeparator() {		
		return CSV_CHAR_SEPARATOR;
	}
	
	@Override
	protected char getCsvQuoteSeparator() {		
		return CSV_QUOTE_SEPARATOR;
	}
	

	@Override
	protected boolean isShortLine(String[] lineItems) {
		return (lineItems.length <= CSV_INDEX_EMAIL);
	}
	
	@Override
	protected Contact getContactFromLineItems(String[] lineItems) {

		String firstname = lineItems[CSV_INDEX_FIRSTNAME].trim();
		String lastname = lineItems[CSV_INDEX_LASTNAME].trim();
		String email = lineItems[CSV_INDEX_EMAIL].trim();
		
		LOGGER.info("got firstname=["+firstname+"] lastname=["+lastname+"] email=["+email+"]");
		
		//--- BR : ignore no-data lines
		if(email.isEmpty() || (firstname.isEmpty() && lastname.isEmpty()) ) {
			LOGGER.debug("no-data line -> IGNORE");
			return null;
		} else {
			LOGGER.debug("data line -> KEEP");
		}
		
		//--- create contact
		Contact contact = new Contact(firstname, lastname, email);		
		return contact;
	}
	
	
	
}
