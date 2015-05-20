package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju 10^x
 * @author jelena
 *
 */
public class Exp10UnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.pow(10.0, value);
	}
	
}
