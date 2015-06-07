package hr.fer.zemris.java.custom.scripting.tokens;

/**
 * Razred izveden iz razreda Token
 * Razred sluzi za pohranu Specijalnih leksema
 * @author Jelena Drzaic
 *
 */
public class TokenSpecial extends Token {
	
	/**
	 * Tip podatka koji reprezentira specijalne lekseme
	 */
	public enum Type {
		/**
		 * {$
		 */
		START_TAG,
		
		/**
		 * $}
		 */
		END_TAG
	}
	
	/**
	 * pohranjen leksem
	 */
	private Type type;
	
	/**
	 * Konstruktor razreda TokenSpecial
	 * prima jednu varijablu tipa Type
	 * @param type specijalni leksem
	 */
	public TokenSpecial(Type type) {
		this.type = type;
	}
	
	/**
	 * Metoda dohvaca pohranjeni leksem
	 * @return pohranjeni leksem tipa Type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Metoda vraca type
	 * u obliku kojem je bio prije parsiranja
	 */
	@Override
	public String toString() {
		return type == Type.START_TAG ? "{$" : "$}";
	}

	@Override
	public void accept(ITokenVisitor visitor) {
		visitor.visitSpecial(this);
	}
}


