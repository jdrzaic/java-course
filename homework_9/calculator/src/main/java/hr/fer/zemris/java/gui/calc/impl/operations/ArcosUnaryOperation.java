package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred implementira sucelje IUnaryOperation
 * Razred implementira operaciju arcos
 * @author jelena
 *
 */
public class ArcosUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.acos(value);
	}
}
