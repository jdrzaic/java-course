package hr.fer.zemris.java.custom.scripting.tokens;
import hr.fer.zemris.java.custom.scripting.tokens.Token;

/**
 * Razred izveden iz razreda Token
 * Razred sluzi za pohranu teksta
 * @author Jelena Drzaic
 */
public class TokenText extends Token {
	/**
	 * pohranjeni tekst
	 */
	String value;
	
	/**
	 * Konstruktor razreda TokenText
	 * Prima jednu varijablu tipa String
	 * @param value
	 */
	public TokenText(String value) {
		this.value = value;
	}
	
	/**
	 * Metoda vraca pohranjeni tekst u varijabli tipa String
	 * @return String
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Metoda vraca vrijednost varijable value
	 */
	@Override
	public String asText() {
		return value;
	}
	
	/**
	 * Metoda vraca varijablu value prije parsiranja
	 */
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < value.length(); ++i) {
			if (value.charAt(i) == '\\' || value.charAt(i) == '{') {
				out += "\\" + value.charAt(i);
			} 
			else {
				out += value.charAt(i);
			}
		}
		return out;
	}
}