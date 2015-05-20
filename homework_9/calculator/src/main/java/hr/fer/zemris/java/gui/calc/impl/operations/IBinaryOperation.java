package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Sucelje reprezentira binarne aritmeticke operacije.
 * @author jelena
 *
 */
public interface IBinaryOperation {
	
	public static final double DELTA = 1E-10;

	/**
	 * Metoda vraca rezultat aritmeticke operacije IBinaryOperation nad
	 * proslijedenim argumentima
	 * @param a prvi operand
	 * @param b drugi operand
	 * @return rezultat aritmeticke operacije
	 */
	public double compute(double a, double b);
}
