package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Razred sluzi za testiranje koristenja design patterna
 * Observer na primjeru klase IntegerStorage.
 * @author Jelena Drzaic
 *
 */
public class ObserverExample {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Parametri su u nastavku;
	 * @param args argumenti komandne linije.
	 */
	public static void main(String[] args) {
		
		IntegerStorageChange istorage = new IntegerStorageChange(
				new IntegerStorage(20), 0, 0);
		
		IntegerStorageObserver observer = new SquareValue();
		istorage.getStorage().addObserver(new ChangeCounter());
		istorage.getStorage().addObserver(new DoubleValue());
		istorage.getStorage().addObserver(observer);
		
		//mijenjaj vrijednosti
		istorage.getStorage().setValue(5);
		istorage.getStorage().setValue(2);
		istorage.getStorage().setValue(25);		
		istorage.getStorage().setValue(13);
		istorage.getStorage().setValue(22);
		istorage.getStorage().setValue(15);
		}
}
