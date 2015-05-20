package hr.fer.zemris.java.custom.scripting.tokens;
/**
 * Bazni razred Tokena
 * Tokeni sluze za reprezentaciju strukturiranih dokumenata
 * @author Jelena Drzaic
 *
 */
public class Token {
	
	/**
	 * Konstruktor razreda Token 
	 */
	public Token() {
		super();
	}
	
	/**
	 * Metoda vraca prazan String
	 * @return string 
	 */
	public String asText() {
		return new String();
	}
	
	/**
	 * Genericka metoda
	 * Metoda vraca razred objekta na kojem je pozvana
	 * @return razred objekta castan u String
	 */
	public String asString() {
		return this.getClass().toString();
	}
	
	/**
	 * Genericka metoda
	 * Metoda vraca razred objekta na kojem je pozvana
	 * @return razred objekta castan u String
	 */
	public String toString() {
		return this.getClass().toString();
	}
}
