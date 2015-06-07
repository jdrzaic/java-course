package hr.fer.zemris.java.custom.scripting.tokens;

/**
 * Bazni razred Tokena.
 * Tokeni sluze za reprezentaciju strukturiranih dokumenata.
 * @author Jelena Drzaic
 *
 */
public abstract class Token {
	
	/**
	 * Metoda vraca prazan String
	 * @return string prazan string
	 */
	public String asText() {
		return new String();
	}
	
	/**
	 * Genericka metoda.
	 * Metoda vraca razred objekta na kojem je pozvana
	 * @return razred objekta castan u String
	 */
	public String asString() {
		return this.getClass().toString();
	}
	
	/**
	 * Genericka metoda.
	 * Metoda vraca razred objekta na kojem je pozvana
	 * @return razred objekta castan u String
	 */
	public String toString() {
		return this.getClass().toString();
	}
	
	/**
	 * Metoda je dio implementacije oblikovnog obrasca visitor.
	 * Kada je pozvana, metoda poziva odgovarajucu metodu na 
	 * instanci {@link ITokenVisitor}-a, ovisno o vrsti tokena. 
	 * @param visitor visitor {@link Token}-a
	 */
	public abstract void accept(ITokenVisitor visitor);
}
