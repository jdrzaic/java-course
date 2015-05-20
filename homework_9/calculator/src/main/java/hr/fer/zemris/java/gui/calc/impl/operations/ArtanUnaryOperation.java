package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju artan
 * @author jelena
 *
 */
public class ArtanUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.atan(value);
	}
}
