package hr.fer.zemris.java.custom.scripting.tokens;
import hr.fer.zemris.java.custom.scripting.tokens.Token;
/**
 * Razred izveden iz razreda Token
 * Pohranjuje jednu varijablu tipa double
 * Sadrzi metodu asText() koja vraca String reprezentaciju pohranjenog broja
 * @author Jelena Drzaic
 *
 */
public class TokenConstantDouble extends Token {
	/**
	 * vrijednost konstante
	 */
	private double value;
	
	/**
	 * Konstruktor razreda
	 * Kao parametar prima jednu varijablu tipa double
	 * @param value vrijednost konstante
	 */
	public TokenConstantDouble(double value) {
		this.value = value;
	}
	
	/**
	 * Metoda vraca vrijednost varijable this.value
	 * @return this.value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Metoda vraca String reprezentaciju varijable value
	 * @return this.value
	 */
	@Override
	public String asText() {
		return Double.toString(value);
	}
	
	/**
	 * Metoda vraca String reprezentaciju varijable value
	 * @return value
	 */
	@Override
	public String toString() {
		return asText();
	}
}
