package hr.fer.zemris.java.custom.collections;

/**
 * Razred implementira EmptyStackException
 * Iznimka je izvedena iz razreda RuntimeException
 * @author Jelena Drzaic
 *
 */
public class EmptyStackException extends RuntimeException {
	
	/**
	 * Defaultni konstruktor razreda EmptyStackException
	 */
	public EmptyStackException() {
		super();
	}
	
	/**
	 * Konstruktor razreda EmptyStackException
	 * Kao parametar prima string koji se proslijeduje korisniku
	 * @param message string poruke koja se proslijeduje korisniku
	 */
	public EmptyStackException(String message) {
		super(message);
	}
	private static final long serialVersionUID =1L;
}