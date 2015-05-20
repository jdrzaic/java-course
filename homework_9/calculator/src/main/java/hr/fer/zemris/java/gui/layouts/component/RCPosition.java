package hr.fer.zemris.java.gui.layouts.component;

/**
 * Razred implementira objekte odredene pozicijom (redak, stupac).
 * Objekt pruza dohvat read-only vrijednosti (retka i stupca) pomocu metoda
 * getRow() i getColumn()
 * @author jelena
 *
 */
public class RCPosition {
	
	/**
	 * broj retka
	 */
	int row;
	
	/**
	 * broj stupca
	 */
	int column;
	
	/**
	 * Kontruktor razreda
	 * @param row broj retka
	 * @param column broj stupca
	 */
	public RCPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Vraca indeks retka objekta
	 * @return redak
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Vraca indeks stupca objekta
	 * @return stupac
	 */
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return "" + "(" + row + "," + column + ")";
	}
}
