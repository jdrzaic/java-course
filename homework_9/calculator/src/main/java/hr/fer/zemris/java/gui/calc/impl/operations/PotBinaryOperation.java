package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IBinaryOperation
 * Razred implementira operaciju potenciranja a^b
 * @author jelena
 *
 */
public class PotBinaryOperation implements IBinaryOperation{

	@Override
	public double compute(double a, double b) {
		return Math.pow(a, b);
	}

}
