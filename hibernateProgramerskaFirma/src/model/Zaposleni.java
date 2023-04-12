package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Zaposleni {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sifraZaposlenog;
	private double plata;
	@OneToOne
	private User user;
	@OneToOne
	private UserDetails userDetails;
	@ManyToOne
	private Tim tim;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Pozicija> pozicije = new ArrayList<Pozicija>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSifraZaposlenog() {
		return sifraZaposlenog;
	}
	public void setSifraZaposlenog(String sifraZaposlenog) {
		this.sifraZaposlenog = sifraZaposlenog;
	}
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public Tim getTim() {
		return tim;
	}
	public void setTim(Tim tim) {
		this.tim = tim;
	}
	public List<Pozicija> getPozicije() {
		return pozicije;
	}
	public void setPozicije(List<Pozicija> pozicije) {
		this.pozicije = pozicije;
	}
	
	

	

}
