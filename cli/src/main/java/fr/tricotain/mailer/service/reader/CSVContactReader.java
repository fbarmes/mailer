package fr.tricotain.mailer.service.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import fr.tricotain.mailer.model.Contact;

import au.com.bytecode.opencsv.CSVReader;

public abstract class CSVContactReader implements ContactReader {

	private static final Logger LOGGER = Logger.getLogger(CSVContactReader.class);
	
	private CSVReader csvReader;

	public CSVContactReader(String filename) throws IOException {
		this(new File(filename));
	}
	
	public CSVContactReader(File file) throws IOException {
		super();	
		LOGGER.debug("Create FileReader using "+file.getAbsolutePath());
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), getCsvFileEncoding());
		
		LOGGER.debug("Create csvReader");
		this.csvReader = new CSVReader(isr, getCsvCharacterSeparator(), getCsvQuoteSeparator());
	}

	
	protected abstract String getCsvFileEncoding();;
	protected abstract char getCsvCharacterSeparator();
	protected abstract char getCsvQuoteSeparator();
	protected abstract Contact getContactFromLineItems(String[] lineItems);
	protected abstract boolean isShortLine(String[] lineItems);
	
	@Override 
	public final List<Contact> readContact() {
		try {
			Map<String, Contact> contactMap = new HashMap<>();
			
			String[] lineItems = null;
			int count = 0;
			while ((lineItems = this.csvReader.readNext()) != null) {
				count++;
				LOGGER.debug("");
				LOGGER.debug("read line("+count+"), items="+lineItems.length);
				
				//-- ignore emtpy line
				if(lineItems.length == 0) {
					continue;
				}
				
				//-- ignore first data line
				if(count==1) {
					continue;
				}
				
				//--- ignore short line
				if(isShortLine(lineItems)) {
					continue;
				}
				
				//--- extract data
				Contact contact = getContactFromLineItems(lineItems);
				if(contact == null) {
					continue;
				}
				
				//--- BR : handle duplicate emails
				//--- TODO : check
				String email = contact.getEmail();
				if(contactMap.containsKey(email)) {
					contactMap.get(email).addFirstname(", ", contact.getFirstname());
				} else {
					contactMap.put(email, contact);
				}
			}
					
			
			List<Contact> contacts = new ArrayList<>(contactMap.values());
			return contacts;
		} catch (IOException e) {
			return null;
		}
	}

	
}
