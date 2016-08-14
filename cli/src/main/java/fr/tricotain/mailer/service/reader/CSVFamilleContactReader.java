package fr.tricotain.mailer.service.reader;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import fr.tricotain.mailer.model.Contact;
import fr.tricotain.mailer.service.utils.StringUtils;

public class CSVFamilleContactReader extends CSVContactReader {

	private static final Logger LOGGER = Logger.getLogger(CSVFamilleContactReader.class);
	
	private static final String FILE_ENCODING = "ISO-8859-1";
	private static final char  CSV_CHAR_SEPARATOR = ';';
	private static final char CSV_QUOTE_SEPARATOR = '"';
	
	private static final int CSV_NO_OF_COLS = 15;
	
	private static final int CSV_INDEX_LASTNAME_MR = 2;
	private static final int CSV_INDEX_FIRSTNAME_MR = 3;
	private static final int CSV_INDEX_LASTNAME_MME = 0;
	private static final int CSV_INDEX_FIRSTNAME_MME = 1;
	
	private static final int CSV_INDEX_EMAIL_FAMILLE = 11;
	private static final int CSV_INDEX_EMAIL_MR = 13;
	private static final int CSV_INDEX_EMAIL_MME = 12;

	private static final int CSV_INDEX_ENFANT = 14;
	
	public CSVFamilleContactReader(File file) throws IOException {
		super(file);
	}

	public CSVFamilleContactReader(String filename) throws IOException {
		super(filename);
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
		return (lineItems.length < CSV_NO_OF_COLS );		
	}
	
	@Override
	protected Contact getContactFromLineItems(String[] lineItems) {
		
		String email = null;
		
		String firstname = catName(lineItems[CSV_INDEX_FIRSTNAME_MR], lineItems[CSV_INDEX_FIRSTNAME_MME]);
		String lastname = catName(lineItems[CSV_INDEX_LASTNAME_MR], lineItems[CSV_INDEX_LASTNAME_MME]);
		

		//--- BR : get email from three choices
		if(StringUtils.isNullOrEmpty(email)) {
			email = lineItems[CSV_INDEX_EMAIL_FAMILLE].trim();
		}
		
		if(StringUtils.isNullOrEmpty(email)) {
			email = lineItems[CSV_INDEX_EMAIL_MR].trim();
		}
		
		if(StringUtils.isNullOrEmpty(email)) {
			email = lineItems[CSV_INDEX_EMAIL_MME].trim();
		}
		
		if(StringUtils.isNullOrEmpty(email)) {
			return null;
		}
		
		String enfant = lineItems[CSV_INDEX_ENFANT].trim();
		
		LOGGER.info("got firstname=["+firstname+"] lastname=["+lastname+"] email=["+email+"], enfant=["+enfant+"]");
		Contact contact = new Contact(firstname, lastname, email);
		contact.appendEnfant(enfant);
				
		return contact;
	}
	

	private final String catName(String str1, String str2) {
	
		StringBuffer sb = new StringBuffer();
		
		if( ! StringUtils.isNullOrEmpty(str1)) {
			sb.append(str1);
		}
		
		if(sb.length() > 0 && !StringUtils.isNullOrEmpty(str2)) {
			sb.append(" / ").append(str2);
		} else if(!StringUtils.isNullOrEmpty(str2)) {
			sb.append(str2);
		}
		
		return sb.toString();
	}
	
}
