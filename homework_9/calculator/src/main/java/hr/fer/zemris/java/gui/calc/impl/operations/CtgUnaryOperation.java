package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju ctg
 * @author jelena
 *
 */
public class CtgUnaryOperation implements IUnaryOperation {

	@Override
	public double compute(double value) {
		if(Math.abs(Math.sin(value)) < DELTA) {
			throw new IllegalArgumentException("Cannot divide by zero");
		}
		return 1 / Math.tan(value);
	}

}
