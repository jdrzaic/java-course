package hr.fer.zemris.java.custom.scripting.nodes;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.tokens.*;

/**
 * Razred naslijeden od razreda Node
 * Sadrzi jednu varijablu tipa Token[] 
 * @author Jelena Drzaic
 */
public class EchoNode extends Node {
	/**
	 * Polje varijabli tipa Token
	 */
	private Token[] tokens;
	
	/**
	 * Konstruktor razreda EchoNode
	 * kao parametar prima polje Tokena
	 * @param tokens polje Tokena
	 */
	public EchoNode(Token[] tokens) {
		this.tokens = tokens;
	}
	
	/**
	 * Metoda vraca polje tokena pohranjenih u instanci razreda
	 * @return polje tokena
	 */
	public Token[] getToken() {
		return tokens;
	}
	
	@Override
	public String toString() {
		String out = "{$=";
		for (int i = 0; i < tokens.length; ++i) {
			out += tokens[i].toString() + " ";
		}
		return out + "$}";
	}
}
