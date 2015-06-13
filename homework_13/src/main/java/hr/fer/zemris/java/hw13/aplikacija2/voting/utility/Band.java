package hr.fer.zemris.java.hw13.aplikacija2.voting.utility;

/**
 * Razred cuva informacije o bandu,
 * tj. njegovo ime i path do pjesme tog banda.
 * @author jelena
 *
 */
public class Band {

	/**
	 * Ime benda
	 */
	private String name;
	
	/**
	 * Url pjesme
	 */
	private String url;

	/**
	 * Konsturkor razreda.
	 * Postavlja vrijednost clanskih varijabli na proslijedene vrijednosti.
	 * @param name ime benda
	 * @param url url pjesme benda
	 */
	public Band(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
	
	/**
	 * Metoda dohvaca ime benda
	 * @return ime banda
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metoda dohvaca url pjesme benda
	 * @return url pjesme benda
	 */
	public String getUrl() {
		return url;
	}	
}
