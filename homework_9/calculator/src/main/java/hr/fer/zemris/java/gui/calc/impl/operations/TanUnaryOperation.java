package hr.fer.zemris.java.gui.calc.impl.operations;

/**
* Razred implementira sucelje IUnaryOperation
* Razred implementira operaciju tan
* @author jelena
*
*/
public class TanUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.tan(value);
	}

}
