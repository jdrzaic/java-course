package hr.fer.zemris.java.hw15.formulars;

import hr.fer.zemris.java.hw15.crypto.DigestChecker;
import hr.fer.zemris.java.tecaj_14.model.BlogUser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Razred sluzi za validaciju parametara dobivenih iz
 * registracijske forme korisnika bloga.
 * @author jelena
 *
 */
public class RegistrationForm {

	/**
	 * Korisnicko ime
	 */
	private String nick;
	
	/**
	 * Ime korisnika
	 */
	private String firstName;
	
	/**
	 * Prezime korisnika
	 */
	private String lastName;
	
	/**
	 * email korisnika
	 */
	private String email;
	
	/**
	 * lozinka korisnika
	 */
	private String password;
	
	/**
	 * Mapa gresaka evidentiranih kod validacije
	 */
	private Map<String, String> errors;
	
	/**
	 * Konstruktor razreda.
	 */
	public RegistrationForm() {
		errors = new HashMap<String, String>();
	}
	
	/**
	 * Metoda sluzi za dohvat korisnickog imena.
	 * @return korisnicko ime
	 */
	public String getNick() {
		return nick;
	}
	
	/**
	 * Metoda postavlja korisnicko ime na proslijedeni parametar.
	 * @param nick string na kojeg postavljamo korisnicko ime
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Metoda sluzi za dohvat imena korisnika.
	 * @return ime korisnika.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Metoda postavlja ime korisnika na proslijedeni parametar.
	 * @param firstName string na koji postavljamo ime korisnika
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Metoda sluzi za dohvat prezimena korisnika.
	 * @return prezime korisnika
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Metoda postavlja prezime korisnika na proslijedeni parametar.
	 * @param lastName string na koji postavljamo prezime korisnika
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Metoda sluzi za dohvat maila korisnika.
	 * @return mail korisnika
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metoda postavlja korisnicki email na proslijedenu vrijednost.
	 * @param email string na koji postavljamo korisnicki email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Metoda sluzi za dohvat lozinke korisnika.
	 * @return lozinka
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Metoda postavlja korisnicku lozinku na proslijedeni parametear, tj.
	 * na temelju njega izracunat digest.
	 * @param password string na koji postavljamo lozinku
	 */
	public void setPassword(String password) {		
		if(password == null || password.length() == 0) {
			this.password = "";
		}else {
			this.password = DigestChecker.calculateSHA(password);
		}
	}
	
	/**
	 * Metoda provjerava postoje li greske u podacima.
	 * @return true ako postoje pogreske, false inace.
	 */
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	/**
	 * Metoda provjerava postoji li greska mapirana na 
	 * proslijedeni parametar.
	 * @param field string za koji provjeravamo pogresku
	 * @return true ako greska postoji, false inace.
	 */
	public boolean hasError(String field) {
		return errors.containsKey(field);
	}
	
	/**
	 * Metoda sluzi za dohvat pogreske mapirane na proslijedeni parametar.
	 * @param field string ciju gresku dohvacamo
	 * @return greska mapirana na proslijedeni parametar
	 */
	public String getError(String field) {
		return errors.get(field);
	}
	
	/**
	 * Metoda vraca prazan string za null, inace trimani proslijedeni argument.
	 * @param str parametar koji modificiramo.
	 * @return modificirani string.
	 */
	private String prepare(String str) {
		return str == null ? "" : str.trim();
	}
	
	/**
	 * Metoda popunjava instancu this parametrima dobivenih iz proslijedenog
	 * {@link HttpServletRequest}-a. Parametri su mapirani na 'nick', 'first.name',
	 * 'last.name', 'email' i 'password'
	 * @param req {@link HttpServletRequest} s pohranjenim parametrima
	 */
	public void fillFromHttpReq(HttpServletRequest req) {
		nick = prepare(req.getParameter("nick"));
		firstName = prepare(req.getParameter("first.name"));
		lastName = prepare(req.getParameter("last.name"));
		email = prepare(req.getParameter("email"));
		setPassword(prepare(req.getParameter("password")));
	}
	
	/**
	 * Metoda provjerava postoje li greske u podacima.
	 * @return true ako greske postoje, false inace
	 */
	public boolean checkErrors() {
		errors.clear();
		if(prepare(nick).length() == 0) {
			errors.put("nick", "Nickname is required!");
		}
		if(prepare(firstName).length() == 0) {
			errors.put("first.name", "First name is required!");
		}
		if(prepare(lastName).length() == 0) {
			errors.put("last.name", "Last name is required!");
		}
		
		if(prepare(email).length() < 3 || !email.contains("@")) {
			errors.put("email", "Valid email is required!");
		}
		if(password.length() == 0) {
			errors.put("password", "Password is required!");
		}
		
		return !errors.isEmpty();
	}
	
	/**
	 * Metoda dohvaca novog {@link BlogUser}-a, ako 
	 * ne postoje greske u podacima, inace null.
	 * @return novokreirani {@link BlogUser}, ili null ako postoje pogreske
	 */
	public BlogUser getNewBlogUser() {
		if(errors.isEmpty()) {
			return new BlogUser(firstName, 
					lastName, 
					nick, 
					email, 
					password
					);
		}
		return null;
	}
	
	/**
	 * Metoda dohvaca mapu pogresaka.
	 * @return mapa pogresaka
	 */
	public Map<String, String> getErrors() {
		return errors;
	}
	
	/**
	 * Metoda resetira instancu, sve parametre postavlja na null.
	 */
	public void reset() {
		this.nick = null;
		this.email = null;
		this.firstName = null;
		this.lastName = null;
	}
}
