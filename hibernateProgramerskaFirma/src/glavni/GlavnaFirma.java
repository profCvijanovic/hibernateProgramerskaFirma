package glavni;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Adresa;
import model.Contact;
import model.Pozicija;
import model.Rola;
import model.Tim;
import model.User;
import model.UserDetails;
import model.Zaposleni;

public class GlavnaFirma {

	public static void main(String[] args) {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setIme("Milos");
		userDetails.setPrezime("Milosevic");
		
		Adresa adresa = new Adresa();
		adresa.setDrzava("Srbija");
		adresa.setGrad("Pozarevac");
		adresa.setUlica("Beogradska 22");
		adresa.setPostanskiBroj("12000");
		
		userDetails.setAdresa(adresa);
		userDetails.setRola(Rola.ADMIN);
		
		Contact kontakt1 = new Contact();
		kontakt1.setTelefon("064111444");
		kontakt1.setEmail("prviMilosevEmail");
		
		Contact kontakt2 = new Contact();
		kontakt2.setTelefon("064222555");
		kontakt2.setEmail("drugiMilosevEmail");
		
		List<Contact> kontakti = new ArrayList<Contact>();
		kontakti.add(kontakt1);
		kontakti.add(kontakt2);
		
		userDetails.setKontakti(kontakti);
		
		Tim tim = new Tim();
		tim.setNaziv("Tim B");
		tim.setOpis("Ovo je Tim B");
		
		Pozicija pozicija1 = new Pozicija();
		pozicija1.setNaziv("Backend Programer");
		
		Pozicija pozicija2 = new Pozicija();
		pozicija2.setNaziv("Frontend Programer");
		
		List<Pozicija> listaPozicija = new ArrayList<Pozicija>();
		listaPozicija.add(pozicija1);
		
		User user = new User();
		user.setUserName("milos");
		user.setPassword("milos123");
		
		Zaposleni zaposleni = new Zaposleni();
		zaposleni.setSifraZaposlenog("8765212");
		zaposleni.setPlata(2000);
		zaposleni.setTim(tim);
		zaposleni.setUser(user);
		zaposleni.setUserDetails(userDetails);
		zaposleni.setPozicije(listaPozicija);
		
		//izgradili smo fabriku
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
			// preuzimamo sesiju iz fabrike sesija
			Session sesija = factory.openSession();
				sesija.beginTransaction();	
					try {	
						// odradi operaciju
						sesija.save(tim);
						sesija.save(pozicija1);
						sesija.save(pozicija2);
						sesija.save(user);
						sesija.save(userDetails);
						sesija.save(zaposleni);
						sesija.getTransaction().commit();
					}catch (Exception e) {
						sesija.getTransaction().rollback();
					}		
			sesija.close();
		factory.close();

	}

}
