package hr.fer.zemris.java.hw14.aplikacija5.model;

/**
 * Razred predstavlja komponentu modela domene.
 * Sluzi za pohranu podataka o opciji ankete.
 * @author jelena
 *
 */
public class PollOptionsEntry {

	/**
	 * id opcije
	 */
	private long id;
	
	/**
	 * ime opcije
	 */
	private String optionTitle;
	
	/**
	 * link opcije
	 */
	private String optionLink;
	
	/**
	 * broj glasova opcije
	 */
	private int votesCount;

	/**
	 * Konstruktor razreda.
	 * @param id id opcije
	 * @param optionTitle ime opcije
	 * @param optionLink link opcije
	 * @param votesCount broj glasova za opciju
	 */
	public PollOptionsEntry(long id, String optionTitle, String optionLink,
			int votesCount) {
		super();
		this.id = id;
		this.optionTitle = optionTitle;
		this.optionLink = optionLink;
		this.votesCount = votesCount;
	}

	/**
	 * Metoda dohvaca id opcije.
	 * @return id opcije
	 */
	public long getId() {
		return id;
	}

	/**
	 * Metoda postavlja id opcije.
	 * @param id id opcije
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Metoda dohvaca ime opcije.
	 * @return ime opcije
	 */
	public String getOptionTitle() {
		return optionTitle;
	}

	/**
	 * Metoda postavlja ime opcije.
	 * @param optionTitle ime opcije
	 */
	public void setOptionTitle(String optionTitle) {
		this.optionTitle = optionTitle;
	}

	/**
	 * Metoda dohvaca link opcije.
	 * @return link opcije
	 */
	public String getOptionLink() {
		return optionLink;
	}

	/**
	 * Metoda postavlja link opcije.
	 * @param optionLink link opcije
	 */
	public void setOptionLink(String optionLink) {
		this.optionLink = optionLink;
	}

	/**
	 * Metoda dohvaca broj glasova opcije.
	 * @return broj glasova opcije
	 */
	public int getVotesCount() {
		return votesCount;
	}

	/**
	 * Metoda postavlja broj glasova opcije.
	 * @param votesCount broj glasova opcije
	 */
	public void setVotesCount(int votesCount) {
		this.votesCount = votesCount;
	}
	
	/**
	 * Metoda povecava broj glasova opcije za jedan.
	 */
	public void incrementVotesCount() {
		this.votesCount++;
	}
}
