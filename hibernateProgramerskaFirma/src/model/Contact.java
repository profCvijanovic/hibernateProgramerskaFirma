package model;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	
	private String telefon;
	private String email;
	
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
