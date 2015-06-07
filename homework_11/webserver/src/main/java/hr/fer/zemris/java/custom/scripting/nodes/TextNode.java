package hr.fer.zemris.java.custom.scripting.nodes;
import hr.fer.zemris.java.custom.scripting.nodes.Node;

/**
 * Razred naslijeden od razreda Node
 * Sadrzi jednu varijablu tipa String
 * @author Jelena Drzaic
 *
 */
public class TextNode extends Node {
	
	/**
	 * varijabla koja sadrzi text zapisan u cvoru
	 */
	private String text;
	
	/**
	 * Konstruktor razreda TextNode
	 * prima jednu varijablu tipa String i postavlja this.text na njenu vrijednost
	 * @param text text na koji postavljamo text cvora
	 */
	public TextNode(String text) {
		super();
		this.text = text;
	}
	
	/**
	 * Metoda sluzi za dohvat sadrzaja cvora
	 * @return this.text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Metoda vraca escapiranu verziju Stringa pohranjenog u 
	 * instanci razreda
	 * @return escapirani String
	 */
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < text.length(); ++i) {
			if (text.charAt(i) == '\\' || text.charAt(i) == '{') {
				out += "\\" + text.charAt(i);
			} 
			else {
				out += text.charAt(i);
			}
		}
		return out;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitTextNode(this);
	}
}
