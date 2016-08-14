package fr.tricotain.mailer.service.reader;

public enum ContactReaderFormat {

	DUMMY,
	ENFANT,
	FAMILLE;
	
	public static ContactReaderFormat fromString(String name) {
		return ContactReaderFormat.valueOf(name.toUpperCase());
	}
	
}
