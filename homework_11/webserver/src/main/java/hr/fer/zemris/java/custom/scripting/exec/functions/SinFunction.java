package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementirana je operacija sin(x).
 * @author jelena
 *
 */
public class SinFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda racuna sin argumenta skinutog sa stacka, te 
	 * vraca rezultat izracuna nazad na stack.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Double x = Double.valueOf(stack.pop().toString());
		x = Math.toDegrees(x);
		double result = Math.sin(x);
		stack.push(result);
	}

}
