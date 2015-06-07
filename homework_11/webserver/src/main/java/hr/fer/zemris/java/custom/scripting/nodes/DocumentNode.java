package hr.fer.zemris.java.custom.scripting.nodes;
import hr.fer.zemris.java.custom.scripting.nodes.Node;

/**
 * Razred izveden iz razreda Node
 * Razred sluzi za pohranu tekstualnih podataka
 * @author jelena
 *
 */
public class DocumentNode extends Node {
	/**
	 * Konstruktor razreda DocumentNode
	 */
	public DocumentNode() {
		super();
	}
	
	/**
	 * Metoda rekonstruira String zapisan u djeci cvora
	 * koristeci escapiranje 
	 * @return escapirani string
	 */
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < numberOfChildren(); ++i) {
			out += getChild(i).toString();
		}
		return out;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitDocumentNode(this);
	}
}
