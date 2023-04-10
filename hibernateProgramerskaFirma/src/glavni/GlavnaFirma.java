package glavni;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Adresa;
import model.Contact;
import model.Rola;
import model.UserDetails;

public class GlavnaFirma {

	public static void main(String[] args) {
		
		UserDetails user = new UserDetails();
		user.setIme("Nenad");
		user.setPrezime("Nenadovic");
		
		Adresa adresa = new Adresa();
		adresa.setDrzava("Srbija");
		adresa.setGrad("Beograd");
		adresa.setUlica("Terazije 22");
		adresa.setPostanskiBroj("11000");
		
		user.setAdresa(adresa);
		user.setRola(Rola.USER);
		
		Contact kontakt1 = new Contact();
		kontakt1.setTelefon("064111222");
		kontakt1.setEmail("prviEmail");
		
		Contact kontakt2 = new Contact();
		kontakt2.setTelefon("064222333");
		kontakt2.setEmail("drugiEmail");
		
		List<Contact> kontakti = new ArrayList<Contact>();
		kontakti.add(kontakt1);
		kontakti.add(kontakt2);
		
		user.setKontakti(kontakti);
		
		//izgradili smo fabriku
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
			// preuzimamo sesiju iz fabrike sesija
			Session sesija = factory.openSession();
				sesija.beginTransaction();	
					try {	
						// odradi operaciju
						sesija.save(user);
						sesija.getTransaction().commit();
					}catch (Exception e) {
						sesija.getTransaction().rollback();
					}		
			sesija.close();
		factory.close();

	}

}
