package glavni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GlavnaFirma {

	public static void main(String[] args) {
		
		//izgradili smo fabriku
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
			// preuzimamo sesiju iz fabrike sesija
			Session sesija = factory.openSession();
				sesija.beginTransaction();	
					try {
						
						// odradi operaciju
						sesija.getTransaction().commit();
					}catch (Exception e) {
						sesija.getTransaction().rollback();
					}		
			sesija.close();
		factory.close();

	}

}
