package hr.fer.zemris.java.gui.charts;

/**
 * Metoda sluzi za pohranu parova vrijednosti tipa int.
 * Nudi metode za dohvat komponenti.
 * @author jelena
 *
 */
public class XYValue {
	
	int x;
	int y;
	
	/**
	 * Konstruktor razreda
	 * @param x prva vrijednost
	 * @param y druga vrijednost
	 */
	public XYValue(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Metoda za dohvat prve vrijednosti.
	 * @return prva vrijednost
	 */
	public int getX() {
		return x;
	}

	/**
	 * Metoda za dohvat druga vrijednosti.
	 * @return druga vrijednost
	 */
	public int getY() {
		return y;
	}
}
