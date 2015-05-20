package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju arctg
 * @author jelena
 *
 */
public class ArctgUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.atan(1 / value);
	}

}
