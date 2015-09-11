package fr.tricotain.mailer.model;

public class Contact {

	private String firstname;
	private String lastname;
	private String email;
	
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




	@Override
	public String toString() {
		return "Contact [firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + "]";
	}


	
}
