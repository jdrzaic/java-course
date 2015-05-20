package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Razred implementira sucelje IntegerStorageObserver.
 * sluzi za ispis kvadrirane vrijednosti instance klase
 * IntegerStorage.
 * @author Jelena Drzaic
 *
 */
public class SquareValue implements IntegerStorageObserver{

	/**
	 * kvadrirana vrijednost
	 */
	private int sValue;
	
	@Override
	public void valueChanged(IntegerStorageChange istorage) {
		valueSquared(istorage);
	}

	/**
	 * Metoda ispisuje kvadrat vrijednosti pohranjene u instanci klase
	 * IntegerStorageChange koja joj je proslijedena.
	 * @param istorage instanca klase IntegerStorageChange
	 */
	private void valueSquared(IntegerStorageChange istorage) {
		sValue = istorage.getValue() * istorage.getValue();
		System.out.println("Provided new value: " + istorage.getValue()
				+ ", square is " + sValue);
	}
}
