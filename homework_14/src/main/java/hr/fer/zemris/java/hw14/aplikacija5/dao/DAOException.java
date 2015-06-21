package hr.fer.zemris.java.hw14.aplikacija5.dao;

/**
 * Pripadna iznimka koristena u implementacijama sucelja
 * {@link DAO}.
 * @author jelena
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * defaultni broj serijalizacije
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Defaultni konstruktor razreda.
	 */
	public DAOException() {}

	/**
	 * Konstruktor razreda.
	 * @param message poruka iznimke
	 * @param cause uzrok iznimke
	 * @param enableSuppression dopustenje
	 * @param writableStackTrace treba li biti ispisan stack trace
	 */
	public DAOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

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

	/**
	 * Konstruktor razreda.
	 * @param cause uzrok iznimke
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
