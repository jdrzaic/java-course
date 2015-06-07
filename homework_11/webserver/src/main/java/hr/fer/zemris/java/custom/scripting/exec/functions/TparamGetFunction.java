package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementira se funkcionalnost dohvata parametra 
 * s odredenim kljucem iz mape trenutnih parametara instance {@link RequestContext}.
 * @author jelena
 *
 */
public class TparamGetFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda dohvaca dvije vrijednosti sa stacka,
	 * u mapi trenutnih parametara {@link RequestContext} trazi vrijednost pod
	 * kljucem druge vrijednosti izvadene sa stacka.
	 * Parametar postavlja na tu vrijednost ako postoji, ili prvu izvadenu vrijednost 
	 * sa stacka.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object dv = stack.pop();
		Object name = stack.pop();
		Object value = context.getTemporaryParameter(name.toString());
		stack.push(value == null ? dv : value);
	}
}
