package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju dijeljenja
 * @author jelena
 *
 */
public class DivideBinaryOperation implements IBinaryOperation {

	@Override
	public double compute(double a, double b) {
		if(Math.abs(b) < DELTA) {
			throw new IllegalArgumentException("Cannot divide by zero");
		}
		return a / b;
	}

}
