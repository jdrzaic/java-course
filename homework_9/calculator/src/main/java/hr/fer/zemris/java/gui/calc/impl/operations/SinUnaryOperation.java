package hr.fer.zemris.java.gui.calc.impl.operations;

/**
* Razred implementira sucelje IUnaryOperation
* Razred implementira operaciju sin
* @author jelena
*
*/
public class SinUnaryOperation implements IUnaryOperation{

	@Override
	public double compute(double value) {
		return Math.sin(value);
	}

}
