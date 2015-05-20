package hr.fer.zemris.java.custom.scripting.tokens;
import hr.fer.zemris.java.custom.scripting.tokens.Token;

/**
 * Razred izveden iz razreda Token
 * Razred sluzi za pohranu operatora
 * Sadrzi metodu asText() koja vraca tu vrijednost
 * @author Jelena Drzaic
 */
public class TokenOperator extends Token {
	
	/**
	 * simbol operatora
	 */
	private String symbol;
	
	/**
	 * Konstruktor razreda TokenOperator
	 * prima jednu vrijednost tipa String i postavlja this.symbol na tu vrijednost
	 * @param vrijednost tipa String
	 */
	public TokenOperator(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Metoda vraca pohranjeni operator
	 * @return this.symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Metoda vraca pohranjeni operator
	 * @return this.value
	 */
	@Override
	public String asText() {
		return symbol;
	}
	
	/**
	 * Metoda vraca pohranjeni operator
	 * @return String symbol
	 */
	@Override
	public String toString() {
		return symbol;
	}
}
