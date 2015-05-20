package hr.fer.zemris.java.gui.calc.impl.operations;

/**
 * Razred imeplementira sucelje IUnaryOperation
 * Implementira promjenu predznaka
 * @author jelena
 *
 */
public class PlusUnaryOperation implements IUnaryOperation {

	@Override
	public double compute(double value) {
		return -value;
	}	
}
