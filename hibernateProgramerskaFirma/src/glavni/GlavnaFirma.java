package glavni;

import java.util.ArrayList;
import java.util.List;

import dao.CrudDao;
import dto.UserZaposleniDto;
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
		
		CrudDao crudDao = new CrudDao();
		
		/*
		 * UserDetails userDetails = new UserDetails(); userDetails.setIme("Pera");
		 * userDetails.setPrezime("Peric");
		 * 
		 * Adresa adresa = new Adresa(); adresa.setDrzava("Srbija");
		 * adresa.setGrad("Novi Sad"); adresa.setUlica("Novosadska 22");
		 * adresa.setPostanskiBroj("21000");
		 * 
		 * userDetails.setAdresa(adresa); userDetails.setRola(Rola.USER);
		 * 
		 * Contact kontakt = new Contact(); kontakt.setTelefon("0638765432");
		 * kontakt.setEmail("perinEmail");
		 * 
		 * List<Contact> kontakti = new ArrayList<Contact>(); kontakti.add(kontakt);
		 * 
		 * userDetails.setKontakti(kontakti);
		 * 
		 * User user = new User(); user.setUserName("pera");
		 * user.setPassword("pera123");
		 * 
		 * Zaposleni zaposleni = new Zaposleni();
		 * zaposleni.setSifraZaposlenog("123456"); zaposleni.setPlata(2700);
		 * zaposleni.setUser(user); zaposleni.setUserDetails(userDetails);
		 * 
		 * crudDao.upisiUserZaposleni(user, userDetails, zaposleni);
		 */
		
		
		//crudDao.promeniPlatuZaposlenom("3000", "3");
		//crudDao.dodeliPozicijuZaposlenom("2", "3");
		//crudDao.obrisiTim("3");
		/*
		 * Zaposleni zaposleni = crudDao.vratiZaposlenogPoId("3");
		 * 
		 * if(zaposleni != null) { System.out.println("Zaposleni je: " +
		 * zaposleni.getSifraZaposlenog()); System.out.println("Username: " +
		 * zaposleni.getUser().getUserName()); System.out.println("Plata: " +
		 * zaposleni.getPlata()); System.out.println("Prezime: " +
		 * zaposleni.getUserDetails().getPrezime()); System.out.println("Grad: " +
		 * zaposleni.getUserDetails().getAdresa().getGrad());
		 * 
		 * for(Contact kontakt: zaposleni.getUserDetails().getKontakti()) {
		 * System.out.println("Email: " + kontakt.getEmail()); }
		 * 
		 * if(zaposleni.getTim() != null) { System.out.println("Tim: " +
		 * zaposleni.getTim().getNaziv()); }else {
		 * System.out.println("Tim nije dodeljen!"); }
		 * 
		 * if(!zaposleni.getPozicije().isEmpty()) { for(Pozicija pozicija:
		 * zaposleni.getPozicije()) { System.out.println("Pozicija: " +
		 * pozicija.getNaziv()); } }else {
		 * System.out.println("Zaposleni nema dodeljenu poziciju..."); }
		 * 
		 * 
		 * }else { System.out.println("Neispravan unos id zaposleni!"); }
		 */
		
		
		/*String sifraZaposlenog = crudDao.vratiSifruZaposlenog("1");
			System.out.println("Sifra je: " + sifraZaposlenog);
			
		String userName = crudDao.vratiUserNameZaUsera("1");
			System.out.println("Username: " + userName);*/
		
		List<User> useriIzBaze = crudDao.vratiSveUsere();
		
		for(User u: useriIzBaze) {
			System.out.println("id: " + u.getId());
			System.out.println("user name: " + u.getUserName());
			System.out.println("pass: " + u.getPassword());
			System.out.println("***********" );
		}
		
		
		List<UserZaposleniDto> podaci = crudDao.vratiNekePodatkeOzaposlenom();
		
		for(UserZaposleniDto d: podaci) {
			System.out.println("plata: " + d.getPlata());
			System.out.println("sifra: " + d.getSifraZaposlenog());
			System.out.println("tim: " + d.getNaziv());
			System.out.println("user name: " + d.getUserName());
			System.out.println("prezime: " + d.getPlata());
			System.out.println("ime: " + d.getIme());
			System.out.println("****************" );
			
		}

	}

}
