package hr.fer.zemris.java.custom.scripting.tokens;

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

	@Override
	public void accept(ITokenVisitor visitor) {
		visitor.visitEquals(this);
	}
}
