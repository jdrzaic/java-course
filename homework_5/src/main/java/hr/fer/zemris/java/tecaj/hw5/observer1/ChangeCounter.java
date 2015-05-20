package hr.fer.zemris.java.tecaj.hw5.observer1;

/**
 * Razred implementira sucelje IntegerStorageObserver.
 * sluzi za pracenje broja promjena na instanci klase 
 * IntegerStorage.
 * @author Jelena Drzaic
 *
 */
public class ChangeCounter implements IntegerStorageObserver {
	
	/**
	 * broj promjena
	 */
	private int count;
	
	/**
	 * Konstruktor razreda
	 * Kreira instancu koja nije registrirala nijednu promjenu.
	 */
	public ChangeCounter() {
		count = 0;
	}
	
	@Override
	public void valueChanged(IntegerStorage istorage) {
		addOne();
	}
	
	/**
	 * Metoda ispisuje broj dosadasnjih promjena.
	 */
	private void addOne() {
		++count;
		System.out.println("Number of value changes since tracking: " 
		+ count);	
	}
}
