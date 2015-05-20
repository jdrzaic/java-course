package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Sucelje reprezentira observere razreda IntegerStorage.
 * Implementira metodu valueChanged, koja registrira promjenu
 * u instanci razreda IntegerStorage na koju je pretplacena.
 * @author Jelena Drzaic
 *
 */
public interface IntegerStorageObserver {
	
	/**
	 * Metoda se poziva kod promjene u klasi na koju je pretplacena.
	 * Obavlja akciju predvidenu u slucaju informacije o promjeni.
	 * @param istorage spremnik promjenu cije clanske varijable
	 * registriramo.
	 */
	public void valueChanged(IntegerStorageChange istorage);
}
