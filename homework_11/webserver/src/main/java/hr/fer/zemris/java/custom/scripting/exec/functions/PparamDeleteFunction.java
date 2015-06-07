package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementira se funkcionalnost brisanja vrijednosti
 * pohranjenih u mapi trajnih parametara {@link RequestContext}.
 * @author jelena
 *
 */
public class PparamDeleteFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda brise vrijednost u mapi trajnih parametara 
	 * {@link RequestContext} na vrijednosti dohvacenoj sa stacka.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object name = stack.pop();
		context.removePersistentParameter(name.toString());
	}
}
