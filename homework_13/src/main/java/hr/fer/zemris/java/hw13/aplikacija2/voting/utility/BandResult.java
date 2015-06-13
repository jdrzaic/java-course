package hr.fer.zemris.java.hw13.aplikacija2.voting.utility;

/**
 * Razred cuva informacije o bendu i glasovima koji je dobio.
 * Nudi gettere svih clanskih varijabi.
 * @author jelena
 *
 */
public class BandResult {
	
	/**
	 * ime benda
	 */
	private String name;
	
	/**
	 * url pjesme benda
	 */
	private String url;
	
	/**
	 * broj glasova benda
	 */
	private int votes;
	
	/**
	 * Konstruktor razreda,
	 * postavlja vrijednost clanskih varijabli na postavljene vrijednosti.
	 * @param name ime benda
	 * @param url url pjesme benda
	 * @param votes glasovi koje je bend dobio
	 */
	public BandResult(String name, String url, int votes) {
		this.name = name;
		this.url = url;
		this.votes = votes;
	}

	/**
	 * Metoda dohvaca url do pjesme benda.
	 * @return url pjesme benda
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Metoda dohvaca broj glasova benda.
	 * @return broj glasova koji je bend dobio
	 */
	public int getVotes() {
		return votes;
	}
	
	/**
	 * Metoda dohvaca ime benda.
	 * @return ime benda
	 */
	public String getName() {
		return name;
	}
	
	
}