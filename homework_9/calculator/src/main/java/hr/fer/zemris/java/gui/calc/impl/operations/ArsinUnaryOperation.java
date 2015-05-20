package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju arsin
 * @author jelena
 *
 */
public class ArsinUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.asin(value);
	}

}
