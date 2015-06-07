package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementira se funkcionalnost postavljanja vrijednosti
 * pohranjenih u mapi trajnih parametara {@link RequestContext}.
 * @author jelena
 *
 */
public class PparamSetFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda dohvaca dvije vrijednosti sa stacka,
	 * u mapi trajnih parametara {@link RequestContext} trazi vrijednost pod
	 * kljucem druge vrijednosti izvadene sa stacka.
	 * Na stack vraca tu vrijednost ako postoji, ili prvu izvadenu vrijednost 
	 * sa stacka.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object name = stack.pop();
		Object value = stack.pop();
		context.setPersistentParameter(name.toString(), value.toString());
	}
	
}
