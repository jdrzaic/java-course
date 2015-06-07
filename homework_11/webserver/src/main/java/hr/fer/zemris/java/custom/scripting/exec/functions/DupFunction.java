package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementirana je fja duplikacije argumenata na vrhu stacka
 * danog u konstruktoru.
 * @author jelena
 *
 */
public class DupFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda duplicira argument s vrha stacka.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object x = stack.pop();
		stack.push(x);
		stack.push(x);
	}

}
