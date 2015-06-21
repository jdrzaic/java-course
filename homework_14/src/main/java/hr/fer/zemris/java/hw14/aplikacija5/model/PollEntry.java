package hr.fer.zemris.java.hw14.aplikacija5.model;

/**
 * Razred predstavlja komponentu modela domene.
 * Sluzi za pohranu podataka o anketi.
 * @author jelena
 *
 */
public class PollEntry {

	/**
	 * id ankete
	 */
	private long id;
	
	/**
	 * Naslov ankete
	 */
	private String title;
	
	/**
	 * Opis ankete
	 */
	private String message;

	/**
	 * Konstruktor razreda.
	 * @param id id ankete
	 * @param title naslov ankete
	 * @param message opis ankete
	 */
	public PollEntry(long id, String title, String message) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
	}

	/**
	 * Metoda dohvaca id ankete.
	 * @return id ankete
	 */
	public long getId() {
		return id;
	}

	/**
	 * Metoda postavlja id ankete.
	 * @param id id ankete
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Metoda dohvaca naslov ankete.
	 * @return naslov ankete
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Metoda postavlja naslov ankete.
	 * @param title naslov ankete
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Metoda dohvaca opis ankete.
	 * @return opis ankete
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Metoda postavlja opis ankete.
	 * @param message opis ankete
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
