package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pozicija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naziv;
	@ManyToMany(mappedBy = "pozicije")
	private List<Zaposleni> listaZaposlenih = new ArrayList<Zaposleni>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<Zaposleni> getListaZaposlenih() {
		return listaZaposlenih;
	}
	public void setListaZaposlenih(List<Zaposleni> listaZaposlenih) {
		this.listaZaposlenih = listaZaposlenih;
	}
	
	
	
	

}
