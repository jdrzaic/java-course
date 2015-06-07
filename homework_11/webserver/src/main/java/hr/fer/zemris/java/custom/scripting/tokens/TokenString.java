package hr.fer.zemris.java.custom.scripting.tokens;

/**
 * Razred izveden iz razreda Token
 * Sluzi za pohranjivanje stringova
 * Sadrzi metodu asText() koja vraca pohranjeni string
 * @author Jelena Drzaic
 *
 */
public class TokenString extends Token {
	
	/**
	 * pohranjeni string
	 */
	private String value;
	
	/**
	 * Konstruktor razreda TokenString
	 * Prima jednu varijablu tipa String
	 * @param value vrijednost stringa
	 */
	public TokenString(String value) {
		this.value = value;
	}
	
	/**
	 * Metoda vraca pohranjeni string
	 * @return this.value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Metoda vraca pohranjeni string
	 * @return this.value
	 */
	@Override
	public String asText() {
		return value;
	}
	
	/**
	 * Metoda vraca string pohranjen u instanci razreda
	 * u obliku kakav je bio prije parsiranja
	 */
	@Override
	public String toString() {
		String out = "\"";
		for (int i = 0; i < value.length(); ++i) {
			char current = value.charAt(i);
			switch (current) {
			case '\\': out += "\\\\"; break;
			case '\n': out += "\\n"; break;
			case '\t': out += "\\t"; break;
			case '\r': out += "\\r"; break;
			case '"': out += "\\\""; break;
			default: out += current;
			}
		}
		return out + "\"";
	}

	@Override
	public void accept(ITokenVisitor visitor) {
		visitor.visitString(this);
	}
	
}
