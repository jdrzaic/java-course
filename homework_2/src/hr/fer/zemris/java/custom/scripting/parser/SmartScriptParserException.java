package hr.fer.zemris.java.custom.scripting.parser;

/**
 * Iznimka naslijedena iz razreda RumtimeException
 * @author Jelena Drzaic
 *
 */
public class SmartScriptParserException extends RuntimeException{
	
	/**
	 * Defaultni konstruktor razreda
	 */
	public SmartScriptParserException() {
		super();
	}
	/**
	 * Konstruktor razreda koji prima poruku namijenjenu korisniku
	 * @param message poruka koja se proslijeduje korisniku
	 */
	public SmartScriptParserException(String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;
}
