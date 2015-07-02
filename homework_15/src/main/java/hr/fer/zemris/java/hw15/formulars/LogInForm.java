package hr.fer.zemris.java.hw15.formulars;

import hr.fer.zemris.java.hw15.crypto.DigestChecker;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Razred sluzi za validaciju parametara dobivenih iz
 * login forme korisnika bloga.
 * @author jelena
 *
 */
public class LogInForm {

	/**
	 * Lozinka korisnika
	 */
	private String password;
	
	/**
	 * Korisnicko ime
	 */
	private String nick;
	
	/**
	 * Greske evidentirane validacijom podataka
	 */
	private Map<String, String> errors;
	
	/**
	 * Konstruktor razreda
	 */
	public LogInForm() {
		errors = new HashMap<String, String>();
	}

	/**
	 * Metoda sluzi za dohvat lozinke.
	 * @return unesena lozinka
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metoda sluzi za postavljanje lozinke na postavljenu vrijednost.
	 * @param password lozinka koju pohranjujemo
	 */
	public void setPassword(String password) {
		if(password != null && password.length() > 0) {
			this.password = DigestChecker.calculateSHA(password);
		}else {
			this.password = "";
		}
	}

	/**
	 * Metoda sluzi za dohvat korisnickog imena.
	 * @return korisnicko ime
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Metoda postavlja korisnicko ime na proslijedenu vrijednost.
	 * @param nick vrijednost na koju postavljamo korisnicko ime
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Metoda sluzi za dohvat gresaka evidentiranih kod validacije forme.
	 * @return mapa gresaka
	 */
	public Map<String, String> getErrors() {
		return errors;
	}
	
	/**
	 * Metoda provjerava postoje li greske u podacima.
	 * @return true ako postoje greske, false inace
	 */
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	/**
	 * Metoda provjerava postoji li greska mapirana na 
	 * string field.
	 * @param field string za kojeg provjeravamo 
	 * @return true ako greska postoji, false inace
	 */
	public boolean hasError(String field) {
		return errors.containsKey(field);
	}
	
	/**
	 * Metoda sluzi za dohvat greske mapirane na proslijedeni parametar.
	 * @param field string za koji dohvacamo gresku.
	 * @return greska, ako postoji, null inace
	 */
	public String getError(String field) {
		return errors.get(field);
	}
	
	/**
	 * Metoda popunjava instancu klase parametrima iz 
	 * {@link HttpServletRequest}-a, koji su mapirani na 'nick'
	 * te 'password'.
	 * @param req instanca {@link HttpServletRequest}-a iz koje dobivamo parametre.
	 */
	public void fillFromHttpReq(HttpServletRequest req)	 {
		setNick(prepare(req.getParameter("nick")));
		setPassword(prepare(req.getParameter("password")));
	}
	
	/**
	 * Metoda vraca prazan string za null, inace trimani proslijedeni argument.
	 * @param parameter parametar koji modificiramo.
	 * @return modificirani string.
	 */
	private String prepare(String parameter) {
		if(parameter == null){
			return "";
		}else{
			return parameter.trim();
		}
	}

	/**
	 * Metoda provjerava postoje li pogreske u
	 * pohranjenim podacima.
	 * @return true ako postoje greske, false inace
	 */
	public boolean checkErrors() {
		errors.clear();
		
		if(nick == null || nick.length() == 0){
			errors.put("nick", "No nickname given!");
		}
		if(password == null || password.length() == 0) {
			errors.put("password", "No password given!");
		}
	
		return !errors.isEmpty();	
	}
}
