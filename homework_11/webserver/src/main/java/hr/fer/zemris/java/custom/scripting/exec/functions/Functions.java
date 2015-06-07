package hr.fer.zemris.java.custom.scripting.exec.functions;

import hr.fer.zemris.java.custom.scripting.tokens.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Razred stvara kolekciju svih dostupnih funkcija, 
 * implementacija sucelja {@link IFunction}.
 * Dana je metoda getForName() koja za zadani token vraca
 * odgovarajucu realizaciju {@link IFunction}-a.
 * @author jelena
 *
 */
public class Functions {

	/**
	 * Mapa koja sadrzi dostupne f-je.
	 */
	private static Map<String, IFunction> functions;
	
	/**
	 * Konstruktor razreda.
	 * Ubacuje u kolekciju sve dostupne implementacije {@link IFunction}.
	 */
	public Functions() {
		functions = new HashMap<String, IFunction>();
		functions.put("sin", new SinFunction());
		functions.put("decfmt", new DecfmtFunction());
		functions.put("dup", new DupFunction());
		functions.put("swap", new SwapFunction());
		functions.put("setMimeType", new SetMymeTextFunction());
		functions.put("paramGet", new ParamGetFunction());
		functions.put("pparamGet", new PparamGetFunction());
		functions.put("pparamSet", new PparamSetFunction());
		functions.put("pparamDel", new PparamDeleteFunction());
		functions.put("tparamGet", new TparamGetFunction());
		functions.put("tparamSet", new TparamSetFunction());
		functions.put("tparamDel", new TparamDeleteFunction());
	}
	
	/**
	 * Metoda vraca instancu implementacije {@link IFunction},
	 * ako takva postoji za proslijedeni {@link Token}.
	 * @param token token po cijem tekstu odredujemo fju za 
	 * povrat.
	 * @return instanca implementacije {@link IFunction},
	 * ako takva postoji, null inace
	 */
	public IFunction getForName(Token token) {
		return functions.get(token.asText());
	}
}
