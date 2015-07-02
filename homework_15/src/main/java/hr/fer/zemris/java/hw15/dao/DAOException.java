package hr.fer.zemris.java.hw15.dao;

/**
 * Pripadna iznimka uz sucelje {@link DAO}.
 * @author jelena
 *
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	/**
	 * Konstruktor razreda.
	 * @param message poruka iznimke
	 * @param cause uzrok iznimke
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Konstruktor razreda.
	 * @param message poruka iznimke
	 */
	public DAOException(String message) {
		super(message);
	}
}
