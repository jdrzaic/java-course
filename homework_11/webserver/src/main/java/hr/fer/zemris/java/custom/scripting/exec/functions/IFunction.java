package hr.fer.zemris.java.custom.scripting.exec.functions;

import java.util.Stack;

import hr.fer.zemris.java.custom.scripting.exec.SmartScriptEngine;
import hr.fer.zemris.java.webserver.RequestContext;

/**
 * Sucelje predstavlja f-je koristene za izvodenje programa koristenjem
 * {@link SmartScriptEngine}.Dana je metoda execute() koja obavlja posao odreden
 * konkretnom implementacijom {@link IFunction}-a. Implementacije su izvedene po
 * pravilima iz teksta zadatka.
 * @author jelena
 *
 */
public interface IFunction {

	/**
	 * Metoda izvrsava funkciju. Zadaca koju izvodi zadana je konkretnom 
	 * implementacijom sucelja {@link IFunction}.
	 * @param stack stack nad kojim provodimo operaciju.
	 * @param context instanca {@link RequestContext}, koju koristimo.
	 */
	public void execute(Stack<Object> stack, RequestContext context);
}
