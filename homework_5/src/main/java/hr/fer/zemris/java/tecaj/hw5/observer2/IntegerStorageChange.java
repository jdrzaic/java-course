package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Razred reprezentira read-only varijantu 
 * razreda InternalStorage.
 * Dodatno, pohranjuje i vrijednost varijable navedene klase
 * prije nego li je doslo do promjene.
 * @author Jelena Drzaic
 *
 */
public class IntegerStorageChange {
	
	/**
	 * Instanca spremnika
	 */
	private IntegerStorage storage;
	
	/**
	 * Vrijednost prije promjene
	 */
	int bValue;
	
	/**
	 * Vrijednost nakon promjene
	 */
	int value;
	
	/**
	 * Konstruktor razreda.
	 * Kao parametre prima varijablu tipa IntegerStorage, te dvije varijable 
	 * tipa int, staru i novu vrijednost.
	 * @param storage spremnik, tipa IntegerStorage
	 * @param bValue stara vrijednost 
	 * @param value nova vrijednost
	 */
	public IntegerStorageChange(IntegerStorage storage, int bValue, int value) {
		this.storage = storage;
		this.bValue = bValue;
		this.value = value;
	}
	
	/**
	 * Metoda vraca instancu klase IntegerStorage pohranjenu 
	 * u instanci klase IntegerStorageChange.
	 * @return IntegerStorage pohranjen u this.
	 */
	public IntegerStorage getStorage() {
		return storage;
	}

	/**
	 * Metoda vraca staru vrijednost pohranjenu unutar 
	 * instance klase na kojoj je pozvana.
	 * @return vrijednost pohranjena u instanci, tipa int.
	 */
	public int getbValue() {
		return bValue;
	}

	/**
	 * Metoda vraca novu vrijednost pohranjenu unutar 
	 * instance klase na kojoj je pozvana.
	 * @return vrijednost pohranjena u instanci, tipa int.
	 */
	public int getValue() {
		return value;
	}
	
}
