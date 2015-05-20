package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju e^x
 * @author jelena
 *
 */
public class ExpUnaryOperation implements IUnaryOperation {

	@Override
	public double compute(double value) {
		return Math.exp(value);
	}

}
