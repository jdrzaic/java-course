package hr.fer.zemris.java.gui.calc.impl.operations;

/**
* Razred implementira sucelje IBinaryOperation
* Razred implementira operaciju zbrajanja
* @author jelena
*
*/
public class PlusBinaryOperation implements IBinaryOperation{

	@Override
	public double compute(double a, double b) {
		return a + b;
	}
}
