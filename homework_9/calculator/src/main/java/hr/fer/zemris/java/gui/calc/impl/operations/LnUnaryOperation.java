package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju ln
 * @author jelena
 *
 */
public class LnUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		if(Math.abs(value) < DELTA) {
			throw new IllegalArgumentException("Ln is not defined for value" + value);
		}
		return Math.log(value);
	}

}
