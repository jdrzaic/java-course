package hr.fer.zemris.java.custom.scripting.tokens;
import hr.fer.zemris.java.custom.scripting.tokens.Token;

/**
 * Razred izveden iz razreda Token
 * Sluzi za reprezentaciju taga '='
 * @author Jelena Drzaic
 *
 */
public class TokenEquals extends Token {
	
	/**
	 * Konstruktor razreda
	 * nema ulaznih parametara
	 */
	public TokenEquals() {
		super();
	}
	
	/**
	 * Vraca String "="
	 */
	@Override
	public String toString() {
		return "=";
	}
}
