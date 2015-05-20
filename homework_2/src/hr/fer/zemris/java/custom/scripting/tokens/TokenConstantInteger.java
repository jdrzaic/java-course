package hr.fer.zemris.java.custom.scripting.tokens;
import hr.fer.zemris.java.custom.scripting.tokens.Token;

/**
 * Razred izveden iz razreda Token
 * Kao varijablu pohranjuje jednu vrijednost tipa int
 * Sadrzi metodu asText() koja vraca String reprezentaciju pohranjenog broja
 * @author Jelena Drzaic
 */
public class TokenConstantInteger extends Token{
	
	/**
	 * vrijednost konstante
	 */
	private int value;
	
	/**
	 * Konstruktor razreda
	 * Kao parametar prima jednu varijablu tipa int
	 * @param value vrijednost konstante
	 */
	public TokenConstantInteger(int value) {
		this.value = value;
	}
	
	/**
	 * Metoda vraca pohranjeni broj
	 * @return this.value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Metoda vraca String reprezentaciju pohranjenog broja
	 * @return this.value
	 */
	@Override
	public String asText() {
		return Integer.toString(value);
	}
	
	/**
	 * Metoda vraca String reprezentaciju pohranjenog broja
	 * @return this.value
	 */
	@Override
	public String toString() {
		return asText();
	}
}
