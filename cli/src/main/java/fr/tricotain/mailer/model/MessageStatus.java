package fr.tricotain.mailer.model;

public enum MessageStatus {

	OK("OK"),
	ERROR("ERROR");
	
	private String code = "";
	
	private MessageStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	
}
