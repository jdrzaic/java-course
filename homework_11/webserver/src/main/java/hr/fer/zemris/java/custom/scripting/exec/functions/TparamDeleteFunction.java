package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementira se funkcionalnost brisanja vrijednosti
 * pohranjenih u mapi trenutnih parametara {@link RequestContext}.
 * @author jelena
 *
 */
public class TparamDeleteFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda brise vrijednost u mapi trenutnih parametara 
	 * {@link RequestContext} na vrijednosti dohvacenoj sa stacka.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object name = stack.pop();
		context.removeTemporaryParameter(name.toString());
	}

}
