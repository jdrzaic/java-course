package hr.fer.zemris.java.tecaj.hw5.observer1;

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
		
		IntegerStorage istorage = new IntegerStorage(20);
		
		IntegerStorageObserver observer = new SquareValue();
		
		istorage.addObserver(observer);
		
		istorage.setValue(5);
		istorage.setValue(2);
		istorage.setValue(25);
		
		istorage.removeObserver(observer);
		
		istorage.addObserver(new ChangeCounter());
		istorage.addObserver(new DoubleValue());
		
		istorage.setValue(13);
		istorage.setValue(22);
		istorage.setValue(15);
		}
}
