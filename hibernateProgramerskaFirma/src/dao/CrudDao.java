package dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;
import model.UserDetails;
import model.Zaposleni;

public class CrudDao {
	
	//izgradili smo fabriku
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public void upisiUserZaposleni(User user, UserDetails userDetails, Zaposleni zaposleni) {
		
		// preuzimamo sesiju iz fabrike sesija
		Session sesija = factory.openSession();
				sesija.beginTransaction();	
					try {	
						// odradi operaciju
						sesija.save(user);
						sesija.save(userDetails);
						sesija.save(zaposleni);
						System.out.println("Upisan user " + user.getUserName());
						System.out.println("Upisan userDetails " + userDetails.getIme());
						System.out.println("Upisan zaposleni " + zaposleni.getSifraZaposlenog());
						sesija.getTransaction().commit();
					}catch (Exception e) {
						sesija.getTransaction().rollback();
					}		
		sesija.close();
		factory.close();
	}
	
	
	public Zaposleni vratiZaposlenogPoId(String id) {
			
		Session sesija = factory.openSession();
			sesija.beginTransaction();	
			try {
				Zaposleni zaposleni = sesija.get(Zaposleni.class, Integer.parseInt(id));
				// ucitavam rucno listu pozicija jer je ona lazy
				Hibernate.initialize(zaposleni.getPozicije());
				sesija.getTransaction().commit();
				return zaposleni;
			}catch (Exception e) {
				sesija.getTransaction().rollback();
				return null;
			}finally {
				sesija.close();
				factory.close();
			}
	}
	

}
