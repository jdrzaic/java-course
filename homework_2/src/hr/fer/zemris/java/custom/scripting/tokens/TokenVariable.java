package hr.fer.zemris.java.custom.scripting.tokens;
import hr.fer.zemris.java.custom.scripting.tokens.Token;

/**
 * Razred izveden iz razreda Token
 * Razred sluzi za pohranu varijabli
 * @author Jelena Drzaic
 *
 */
public class TokenVariable extends Token {
	
	/**
	 * Ime varijable
	 */
	private String name;
	
	/**
	 * Konstruktor razreda TokenVariable
	 * Prima jednu varijablu tipa String
	 */
	public TokenVariable(String name) {
		this.name = name;
	}
	
	/**
	 * Metoda vraca vrijednost pohranjene varijable
	 * @return value
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Metoda vraca vrijednost pohranjene varijable
	 * @return value
	 */
	@Override
	public String asText() {
		return name;
	}
	
	/**
	 * Metoda vraca name 
	 * @return String name
	 */
	@Override
	public String toString() {
		return name;
	}
}
