package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dto.UserZaposleniDto;
import model.Pozicija;
import model.Tim;
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
	
	
	public void dodeliTimZaposlenom(String idTima, String idZaposlenog) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();	
		try {
			
			Zaposleni zaposleni = sesija.get(Zaposleni.class, Integer.parseInt(idZaposlenog));
			Tim tim = sesija.get(Tim.class, Integer.parseInt(idTima));
			zaposleni.setTim(tim);	
			sesija.update(zaposleni);
			
			sesija.getTransaction().commit();
		}catch (Exception e) {
			sesija.getTransaction().rollback();
		}finally {
			sesija.close();
		}	
	}
	
	
	public void promeniPlatuZaposlenom(String plata, String idZaposlenog) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();	
		try {
			
			Zaposleni zaposleni = sesija.get(Zaposleni.class, Integer.parseInt(idZaposlenog));	
			zaposleni.setPlata(Double.parseDouble(plata));	
			sesija.update(zaposleni);
			
			sesija.getTransaction().commit();
		}catch (Exception e) {
			sesija.getTransaction().rollback();
		}finally {
			sesija.close();
		}	
	}
	
	public void dodeliPozicijuZaposlenom(String idPozicije, String idZaposlenog) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();	
		try {
			
			Zaposleni zaposleni = sesija.get(Zaposleni.class, Integer.parseInt(idZaposlenog));
			Hibernate.initialize(zaposleni.getPozicije());
			Pozicija pozicija = sesija.get(Pozicija.class, Integer.parseInt(idPozicije));
			
			zaposleni.getPozicije().add(pozicija);
			
			sesija.update(zaposleni);
			
			sesija.getTransaction().commit();
		}catch (Exception e) {
			sesija.getTransaction().rollback();
		}finally {
			sesija.close();
		}	
	}
	
	
	public void obrisiTim(String idTima) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();	
		try {
			
			Tim tim = sesija.get(Tim.class, Integer.parseInt(idTima));	
			sesija.delete(tim);
			System.out.println("Tim sa id-jem " + idTima + " je obrisan!");
			sesija.getTransaction().commit();
		}catch (Exception e) {
			sesija.getTransaction().rollback();
		}finally {
			sesija.close();
		}	
	}
	
	
	public String vratiSifruZaposlenog(String idZaposlenog) {
		
		Session sesija = factory.openSession();
			sesija.beginTransaction();	
			try {
				//Hibernate Query Language (HQL)
				String hql = "SELECT sifraZaposlenog FROM Zaposleni WHERE id = :idZaposleni";
				Query query = sesija.createQuery(hql);
				query.setParameter("idZaposleni", Integer.parseInt(idZaposlenog));
				String sifra = (String)query.getSingleResult();
	
				sesija.getTransaction().commit();
				return sifra;
			}catch (Exception e) {
				sesija.getTransaction().rollback();
				return null;
			}finally {
				sesija.close();
			}
	}
	
	
	public String vratiUserNameZaUsera(String idUser) {
		
		Session sesija = factory.openSession();
			sesija.beginTransaction();	
			try {
				//Hibernate Query Language (HQL)
				String hql = "SELECT userName FROM User WHERE id = :idUsera";
				Query query = sesija.createQuery(hql);
				query.setParameter("idUsera", Integer.parseInt(idUser));
				String userName = (String)query.getSingleResult();
	
				sesija.getTransaction().commit();
				return userName;
			}catch (Exception e) {
				sesija.getTransaction().rollback();
				return null;
			}finally {
				sesija.close();
			}
	}
	
	public List<User> vratiSveUsere() {
		
		List<User> sviUseri = new ArrayList<User>();
		
		Session sesija = factory.openSession();
			sesija.beginTransaction();	
			try {
				//Hibernate Query Language (HQL)
				String hql = "FROM User";
				Query query = sesija.createQuery(hql);	
				sviUseri = query.getResultList();
	
				sesija.getTransaction().commit();
				return sviUseri;
			}catch (Exception e) {
				sesija.getTransaction().rollback();
				return null;
			}finally {
				sesija.close();
			}
	}
	
	public List <UserZaposleniDto> vratiNekePodatkeOzaposlenom() {
		
		List<UserZaposleniDto> sviPodaci = new ArrayList<UserZaposleniDto>();
		
		Session sesija = factory.openSession();
			sesija.beginTransaction();	
			try {
				//SQL
				String sql = "SELECT \n"
						+ "z.plata,\n"
						+ "z.sifraZaposlenog,\n"
						+ "t.naziv,\n"
						+ "u.userName,\n"
						+ "ud.prezime,\n"
						+ "ud.ime\n"
						+ "FROM zaposleni z\n"
						+ "INNER JOIN tim t on z.tim_id = t.id\n"
						+ "INNER JOIN user u on z.user_id = u.id\n"
						+ "INNER JOIN userdetails ud on z.userDetails_id = ud.id";
				Query query = sesija.createNativeQuery(sql);	
				List<Object[]> sviRedovi = query.getResultList();
				
				for(Object[] o : sviRedovi) {
					
					UserZaposleniDto dto = new UserZaposleniDto();
					
					dto.setPlata((double)o[0]);
					dto.setSifraZaposlenog((String)o[1]);
					dto.setNaziv((String)o[2]);
					dto.setUserName((String)o[3]);
					dto.setPrezime((String)o[4]);
					dto.setIme((String)o[5]);
					
					sviPodaci.add(dto);
				}
				
				sesija.getTransaction().commit();
				return sviPodaci;
			}catch (Exception e) {
				sesija.getTransaction().rollback();
				return null;
			}finally {
				sesija.close();
			}
	}
	
	
	

}
