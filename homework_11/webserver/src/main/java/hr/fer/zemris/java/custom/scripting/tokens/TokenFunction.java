package hr.fer.zemris.java.custom.scripting.tokens;

/**
 * Razred izveden iz razreda Token
 * sluzi za pohranu izraza tipa String koji predstavljaju funkcije
 * Sadrzi metodu asText() koja vraca String reprezentaciju te varijable
 * @author Jelena Drzaic
 */
public class TokenFunction extends Token {
	
	/**
	 * Ime varijable
	 */
	private String name;
	
	/**
	 * Konstruktor razreda TokenVariable
	 * Prima String ime koji reprezentira funkciju
	 * @param name ime funkcije
	 */
	public TokenFunction(String name) {
		this.name = name;
	}
	
	/**
	 * Metoda vraca funkciju pohranjenu u instanci klase na 
	 * kojoj je pozvana
	 * @return this.value
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Metoda vraca funkciju pohranjenu u instanci klase na 
	 * kojoj je pozvana
	 * @return this.value
	 */
	@Override
	public String asText() {
		return name;
	}
	
	/**
	 * Metoda vraca funkciju pohranjenu u instanci klase na 
	 * kojoj je pozvana u obliku prije parsiranja
	 * @return this.value
	 */
	@Override
	public String toString() {
		return "@" + name;
	}

	@Override
	public void accept(ITokenVisitor visitor) {
		visitor.visitFunction(this);
	}
}
