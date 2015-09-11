package fr.tricotain.mailer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.log4j.Logger;

import fr.tricotain.mailer.model.Config;
import fr.tricotain.mailer.service.Mailer;

public class Main {

	public static final Logger LOGGER = Logger.getLogger(Main.class);
	
	private static String configFile;
	private static String messageFile;
	private static String contactFile;
	private static String attachmentFile;
	
	public static void main(String[] args) {
		processArguments(args);
		Mailer mailer =  new Mailer();
		mailer.run();
		
	}
	
	
	private static void processArguments(String[] args) {
		
		if(args.length < 3) {
			LOGGER.error("Usage : <config file> <message file> <contact file>");
			System.exit(0);
		}
		
		configFile = args[0];
		messageFile = args[1];
		contactFile = args[2];
		
		if(args.length == 4) {
			attachmentFile = args[3];
		}
		
		try {
			loadPropertiesFromFile(configFile);
			loadMessageFromFile(messageFile);
			loadContactFromFile(contactFile);
			
			if(attachmentFile != null) {
				loadAttachmentFromFile(attachmentFile);
			}
			
		} catch (IOException e) {
			LOGGER.error(e);
			System.exit(0);
		}
	}
	
	
	private static void loadPropertiesFromFile(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filename));
		Properties properties = new Properties();
		properties.load(fis);
		Config.getInstance().setProperties(properties);	
	}

	private static void loadMessageFromFile(String filename) throws IOException {
		String message = readFile(filename, Config.getInstance().getMailMessageFileEncoding());
		Config.getInstance().setMailBody(message);
	}
	
	private static void loadContactFromFile(String filename) throws IOException {
		File file = new File(filename);
		if(!file.exists()) {
			throw new IOException("file does not exist : " + filename);
		}
		Config.getInstance().setCsvFileName(filename);
	}

	private static void loadAttachmentFromFile(String filename) throws IOException {
		File file = new File(filename);
		if(!file.exists()) {
			throw new IOException("file does not exist : " + filename);
		}
		Config.getInstance().setMailAttachmentFilename(filename);
	}

	
	static String readFile(String path, String encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));		
		return new String(encoded, encoding);
	}
}
 