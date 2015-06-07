package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.webserver.RequestContext;

import java.text.DecimalFormat;
import java.util.Stack;

/**
 * Razred implementira sucelje {@link IFunction}.
 * Implementirana je fja formatiranja broja u skladu s danim 
 * {@link DecimalFormat}.
 * @author jelena
 *
 */
public class DecfmtFunction implements IFunction{

	/**
	 * {@inheritDoc}
	 * Metoda skida decimal format te broj za formatiranje sa stacka,
	 * vrsi formatiranje argumenta te ga stavlja nazad na stack.
	 */
	@Override
	public void execute(Stack<Object> stack, RequestContext context) {
		Object f = stack.pop();
		Object x = stack.pop();
		DecimalFormat decfmt = new DecimalFormat(f.toString());
		stack.push(decfmt.format(x));
	}

}
