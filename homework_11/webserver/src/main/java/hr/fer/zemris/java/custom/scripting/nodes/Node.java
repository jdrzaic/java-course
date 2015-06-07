package hr.fer.zemris.java.custom.scripting.nodes;
import hr.fer.zemris.java.custom.collections.ArrayBackedIndexedCollection;
/**
 * Razred reprezentira cvor stabla
 * Sadrzi metode za ubacivanje djeteta,
 * dohvacanje broja (direktne) djece
 * 
 * @author Jelena Drzaic
 *
 */
public abstract class Node {
	
	/**
	 * Kolekcija djece cvora this
	 */
	ArrayBackedIndexedCollection children;
	
	/**
	 * Metoda dodaje dijete u kolekciju children
	 * @param child dijete koje ubacujemo
	 */
	public void addChildNode(Node child) {
		if(children == null) {
			children = new ArrayBackedIndexedCollection(1);
		}
		children.add((Object)child);
	}
	
	/**
	 * Metoda vraca broj direktne djece cvora
	 * @return broj djece cvora this
	 */
	public int numberOfChildren() {
		if(children == null) {
			return 0;
		}
		return children.size();
	} 
	
	/**
	 * Metoda vraca index-to dijete cvora.
	 * @param index koje po redu dijete vracamo
	 * @return index-to dijete.
	 */
	public Node getChild(int index) {
		return (Node)children.get(index);
	}
	
	/**
	 * Metoda prihvaca proslijedeni visitor te poziva metodu 
	 * koja odraduje odredeni posao za konkretnu implementaciju ovog
	 * apstraktnog razreda.
	 * @param visitor instanca implementacije sucelja {@link INodeVisitor}
	 */
	public abstract void accept(INodeVisitor visitor);
	
	/**
	 * Metoda vraca string reprezentaciju cvora.
	 * @return string reprezentacija cvora
	 */
	public abstract String toString();
}
