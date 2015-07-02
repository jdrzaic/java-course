package hr.fer.zemris.java.hw15.formulars;

import hr.fer.zemris.java.tecaj_14.model.BlogEntry;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Razred sluzi za validaciju podataka dobivenih iz web formulara.
 * Pohranjuje podatke vezane uz jedan unos bloga.
 * @author jelena
 *
 */
public class BlogEntryForm {
	
	/**
	 * Naslov unosa bloga
	 */
	private String title;
	
	/**
	 * Sadrzaj unosa bloga
	 */
	private String text;
	
	/**
	 * Mapa greske i opisa greske nastalih validacijom podataka
	 * po zadanim specifikacijama
	 */
	private Map<String, String> errors;
	
	/**
	 * Konstruktor razreda.
	 * Postavlja naslov i sadrzaj unosa na prazan string.
	 */
	public BlogEntryForm() {
		super();
		this.title = "";
		this.text = "";
		this.errors = new HashMap<String, String>();
	}

	/**
	 * Metoda dohvaca naslov unosa bloga.
	 * @return naslov unosa bloga
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Metoda postavlja naslov unosa bloga na proslijedenu vrijednost.
	 * @param title string na kojeg postavljamo naslov unosa bloga
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Metoda sluzi za dohvat sadrzaja unosa bloga.
	 * @return sadrzaj unosa bloga
	 */
	public String getText() {
		return text;
	}

	/**
	 * Metoda postavlja sadrzaj unosa bloga na proslijedenu vrijednost.
	 * @param text  text na koji postavljamo text unosa bloga
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Metoda dohvaca mapu greski evidentiranih prilikom validacije podataka.
	 * @return mapa greski
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * Metoda postavlja greske na proslijedenu vrijednost.
	 * @param errors mapa greski
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	/**
	 * Metoda provjerava ima li greska u podacima.
	 * @return true ako greska postoji, false inace
	 */
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	/**
	 * Metoda provjerava postoji li greska u podatcima,
	 * mapirana na key koji je jednak proslijdenoj vrijednosti.
	 * @param field string za kojeg trazimo informaciju o pogreski
	 * @return true ako postoji pogreska, false inace
	 */
	public boolean hasError(String field) {
		return errors.containsKey(field);
	}
	
	/**
	 * Metoda dohvaca opis greske mapirane na proslijedeni parametar.
	 * @param field parametar za koji trazimo opis pogreske
	 * @return opis pogreske mapirane na field
	 */
	public String getError(String field) {
		return errors.get(field);
	}
	
	/**
	 * Metoda popunjava instancu klase iz parametara dobivenih iz
	 * {@link HttpServletRequest}-a, mapiranih na 'title' i 'text.'
	 * @param req instanca {@link HttpServletRequest} koji ima pohranjene 
	 * parametre za validaciju
	 */
	public void fillFromHttpReq(HttpServletRequest req) {
		this.title = prepare(req.getParameter("title"));
		this.text = prepare(req.getParameter("text"));
	}
	
	/**
	 * Metoda za null vraca prazan string, inace vraca trimani parametar.
	 * @param param string kojeg modificiramo
	 * @return modificirani string
	 */
	private String prepare(String param) {
		if(param == null) {
			return "";
		}
		return param.trim();
	}
	
	/**
	 * Metoda provjerava postoji li greska u podacima pohranjenim 
	 * u insanci this. Provjerava se je li svaki od parametara neprazan string.
	 * @return true ako u parametrima ima pogresaka, false inace
	 */
	public boolean checkErrors() {
		errors.clear();
		
		if(prepare(title).length() == 0) {
			errors.put("title", "Title required!");
		}
		if(prepare(text).length() == 0) {
			errors.put("text", "Text is required!");
		}
		
		return !errors.isEmpty();
	}
	
	/**
	 * Metoda dohvaca novu instancu {@link BlogEntry}
	 * nastalog iz parametara pohranjenih u instanci this.
	 * @return novokreirani {@link BlogEntry}
	 */
	public BlogEntry getBlogEntry() {
		BlogEntry entry = new BlogEntry();
		entry.setTitle(this.title);
		entry.setText(this.text);
		return entry;
	}
}
