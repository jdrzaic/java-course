package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementira se funkcionalnost zamjene poredaka elemenata na stacku.
 * Zamjena se vrsi nad prva dva dohvacena elementa stacka.
 * @author jelena
 *
 */
public class SwapFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda uzima sa stacka dva argumenta, te ih vraca na stack 
	 * u suprotnom redoslijedu od prijasnjeg.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object a = stack.pop();
		Object b = stack.pop();
		stack.push(a);
		stack.push(b);
	}

}
