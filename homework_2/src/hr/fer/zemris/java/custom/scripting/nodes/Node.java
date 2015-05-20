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
public class Node {
	
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
	public Node getChild(int index) {
		return (Node)children.get(index);
	}
	public String toString() {
		return getClass().toString();
	}
}
