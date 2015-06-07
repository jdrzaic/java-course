package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementira se funkcionalnost postavljanja varijable mymeText
 * u {@link RequestContext} na neku vrijednost.
 * @author jelena
 *
 */
public class SetMymeTextFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda dohvaca vrijednost sa stacka te posavlja mymeType
	 * varijablu na tu vrijednost.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		String x = stack.pop().toString();
		context.setMimeType(x);
	}

}
