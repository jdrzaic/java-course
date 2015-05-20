package hr.fer.zemris.java.gui.calc.impl.operations;

/**
* Razred implementira sucelje IBinaryOperation
* Razred implementira operaciju korijenovanja
* @author jelena
*
*/
public class RootBinaryOperation implements IBinaryOperation{

	@Override
	public double compute(double a, double b) {
		return Math.pow(a, 1 / b);
	}

}
