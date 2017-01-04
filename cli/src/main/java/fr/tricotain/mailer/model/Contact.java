package fr.tricotain.mailer.model;

import fr.tricotain.mailer.service.utils.StringUtils;

public class Contact {

	private String firstname;
	private String lastname;
	private String email;
	
	private String enfant;
	
	public Contact() {
		super();
	}

	public Contact(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public void addFirstname(String separator, String firstname) {
		this.firstname += separator + firstname;
	}

	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getEnfant() {
		return enfant;
	}

	public void setEnfant(String enfant) {
		this.enfant = enfant;
	}

	
	public  void appendEnfant(String str) {
		
		if(StringUtils.isNullOrEmpty(enfant)) {
			this.setEnfant(str);
		} else {
			this.enfant += str;
		}
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("Contact : ");
		sb.append(" firstname=["+firstname+"]");
		sb.append(" lastname=["+lastname+"]");
		sb.append(" email=["+email+"]");
		sb.append(" enfant=["+enfant+"]");
		
		return sb.toString();
	}


	
}
