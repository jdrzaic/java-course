package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju cos
 * @author jelena
 *
 */
public class CosUnaryOperation implements IUnaryOperation {

	@Override
	public double compute(double value) {
		return Math.cos(value);
	}

}
