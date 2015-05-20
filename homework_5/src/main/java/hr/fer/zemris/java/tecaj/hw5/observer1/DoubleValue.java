package hr.fer.zemris.java.tecaj.hw5.observer1;

/**
 * Razred implementira sucelje IntegerStorageObserver.
 * sluzi za ispis dvostrukih vrijednosti instance klase
 * IntegerStorage.
 * @author Jelena Drzaic
 *
 */
public class DoubleValue implements IntegerStorageObserver{
	/**
	 * broj promjena
	 */
	int count;
	
	/**
	 * Dvostruka vrijednost
	 */
	int dValue;
	
	/**
	 * Konstruktor razreda
	 * Kreira intancu koja nije registrirala nijednu promjenu dosad.
	 */
	public DoubleValue() {
		count = 0;
	}
	
	@Override
	public void valueChanged(IntegerStorage istorage) {
		printDouble(istorage);
	}
	
	/**
	 * Metoda ispisuje dvostruku vrijednost varijable zapisane u
	 * proslijedenoj instanci klase IntegerStorageChange.
	 * Također, ona se nakon dvije promjene odjavljuje s proslijedene
	 * instance klase.
	 * @param istorage instanca klase IntegerStorageChange
	 */
	private void printDouble(IntegerStorage istorage) {
		
		dValue = istorage.getValue() * 2;
	
		System.out.println("Double value: " + dValue);
		++count;
		if(count == 2) {
			istorage.removeObserver(this);
		}
	}
}
