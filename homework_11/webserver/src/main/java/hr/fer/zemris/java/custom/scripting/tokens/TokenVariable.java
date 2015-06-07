package hr.fer.zemris.java.custom.scripting.tokens;

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
	 * @param name naziv varijable
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

	@Override
	public void accept(ITokenVisitor visitor) {
		visitor.visitVariable(this);
	}
}
