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

import au.com.bytecode.opencsv.CSVReader;
import fr.tricotain.mailer.model.Config;
import fr.tricotain.mailer.model.Contact;

public class CSVContactReader implements ContactReader {

	private static final Logger LOGGER = Logger.getLogger(CSVContactReader.class);
	
	
	private CSVReader csvReader;
	
	public CSVContactReader(String filename) throws IOException {
		this(new File(filename), Config.getInstance().getCsvFileCharacterSeparator(), Config.getInstance().getCsvFileQuoteSeparator());
	}
	
	
	public CSVContactReader(File file) throws IOException {
		this(file, Config.getInstance().getCsvFileCharacterSeparator(), Config.getInstance().getCsvFileQuoteSeparator());
	}
	
	public CSVContactReader(File file, char charSeparator, char quoteCharacter) throws IOException {
		super();	
		LOGGER.debug("Create FileReader using "+file.getAbsolutePath());
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), Config.getInstance().getCsvFileEncoding());
		
		LOGGER.debug("Create csvReader");
		this.csvReader = new CSVReader(isr, charSeparator, quoteCharacter);
	}
	
	
	
	
	@Override 
	public List<Contact> readContact() {
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
				if(lineItems.length <= Config.getInstance().getCsvFileIndexEmail()) {
					continue;
				}
				
				//--- extract data
				String firstname = lineItems[Config.getInstance().getCsvFileIndexFirstName()].trim();
				String lastname = lineItems[Config.getInstance().getCsvFileIndexLastName()].trim();
				String email = lineItems[Config.getInstance().getCsvFileIndexEmail()].trim();
				
				LOGGER.debug("got firstname=["+firstname+"] lastname=["+lastname+"] email=["+email+"]");
				
				//--- BR : ignore no-data lines
				if(email.isEmpty() || (firstname.isEmpty() && lastname.isEmpty()) ) {
					LOGGER.debug("no-data line -> IGNORE");
					continue;
				} else {
					LOGGER.debug("data line -> KEEP");
				}
				
				//--- create contact
				Contact contact = new Contact(firstname, lastname, email);
				
				//--- BR : handle duplicate emails
				if(contactMap.containsKey(email)) {
					contactMap.get(email).addFirstname(", ", firstname);
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
