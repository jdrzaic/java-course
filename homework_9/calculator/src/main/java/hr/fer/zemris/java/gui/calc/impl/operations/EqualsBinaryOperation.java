package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju =.
 * @author jelena
 *
 */
public class EqualsBinaryOperation implements IBinaryOperation{

	@Override
	public double compute(double a, double b) {
		return b;
	}
}
