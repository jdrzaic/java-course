package hr.fer.zemris.java.custom.collections;
import hr.fer.zemris.java.custom.collections.ArrayBackedIndexedCollection;
import hr.fer.zemris.java.custom.collections.EmptyStackException;

/**
 * Razred reprezentira apstraktni tip podataka Stack
 * Razred posjeduje metode push() za ubacivanje elementa
 * pop() za izbacivanje elementa, peek() za dohvacanje elementa
 * s vrha stacka, provjeru je li stack prazan te velicinu stacka
 * @author jelena
 *
 */
public class ObjectStack {
	
	/**
	 * Spremnik elemenata tipa ArrayBackedIndexCollection
	 */
	ArrayBackedIndexedCollection elementi = new ArrayBackedIndexedCollection();
	
	/**
	 * Konstruktor razreda ObjectStack
	 * stvara prazan stack
	 */
	public ObjectStack() {
		super();
	}
	/**
	 * Metoda provjerava je li stack na kojem je pozvana prazan
	 * Slozenost je O(1)
	 * @return true ako je stack prazan, false inace
	 */
	public boolean isEmpty() {
		if(elementi.size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Metoda vraca velicinu stacka na kojem je pozvana
	 * Slozenost je O(1)
	 * @return broj elemenata pohranjenih na stacku
	 */
	public int size() {
		return elementi.size();
	}
	
	/**
	 * Metoda ubacuje objekt value na vrh stacka
	 * SLozenost je O(1)
	 * @param value vrijednost koju dodajemo u stack
	 */
	public void push(Object value) {
		elementi.add(value);
	}
	
	/**
	 * Metoda izbacuje element s vrha stacka
	 * Slozenost O(1)
	 * @return izbaceni objekt
	 */
	public Object pop() {
		if(elementi.size() == 0) {
			throw new EmptyStackException("Stack is empty");
		}
		Object top = this.peek();
		elementi.remove(elementi.size() - 1);
		return top;
	}
	
	/**
	 * Metoda dohvaca element s vrha stacka
	 * Pritom element ostaje na stacku
	 * Slozenost je O(1)
	 * @return objekt s vrha stacka
	 */
	public Object peek() {
		if(this.isEmpty()) {
			throw new EmptyStackException();
		}
		return elementi.get(elementi.size() - 1);
	}
	
	/**
	 * Metoda prazni stack na kojem je pozvana
	 * Slozenost O(N)
	 */
	public void clear() {
		elementi.clear();
	}
}
