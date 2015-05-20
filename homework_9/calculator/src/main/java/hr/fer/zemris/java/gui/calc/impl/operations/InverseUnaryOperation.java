package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju 1/x
 * @author jelena
 *
 */
public class InverseUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		if(Math.abs(value) < DELTA) {
			throw new IllegalArgumentException("Cannot divide by zero");
		}
		return 1 / value;
	}

	
}
