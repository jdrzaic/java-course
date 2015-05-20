package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Sucelje reprezentira unarne operacije.
 * @author jelena
 *
 */
public interface IUnaryOperation {
	
	public static final double DELTA = 1E-10;

	/**
	 * Metoda vraca rezultat djelovanja unarne operacije na
	 * proslijedeni argument
	 * @param value vrijednost za koju  racunamo djelovanje
	 * @return rezultat operacije
	 */
	public double compute(double value);
}
