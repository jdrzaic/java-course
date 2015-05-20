package hr.fer.zemris.java.tecaj.hw5.observer2;

import java.util.LinkedList;
import java.util.List;

/**
 * Razred sluzi za pohranjivanje vrijednosti-
 * jednog cijelog broja.
 * Razred reprezentira Subject u oblikovnom obrascu
 * "Observer Design Pattern".
 * Rzred nudi metodu za postavljanje vrijednosti,
 * @author Jelena Drzaic
 *
 */
public class IntegerStorage {
		
	/**
	 * pohranjena vrijednost
	 */
	private int value;
	
	/**
	 * Lista observera
	 */
	private List<IntegerStorageObserver> observers;
	
	/**
	 * Konstruktor razreda.
	 * Prima jednu vrijednost tipa int te postavlja svoju 
	 * clansku varijablu na tu vrijednost.
	 * @param initialValue vrijednost koju spremamo u instancu klase.
	 */
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
	}
	
	/**
	 * Metoda koja sluzi za dodavanje observera na instancu klase
	 * na kojoj je pozvana.
	 * @param observer observer koji dodajemo.
	 */
	public void addObserver(IntegerStorageObserver observer) {
	// add the observer in observers if not already there ...
		if(observers == null) {
			observers = new LinkedList<IntegerStorageObserver>();
			observers.add(observer);
		}else if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}
	
	/**
	 * Metoda mice observer iz liste observera instance klase na 
	 * kojoj je pozvana. 
	 * @param observer observer kojeg izbacujemo.
	 */
	public void removeObserver(IntegerStorageObserver observer) {
	// remove the observer from observers if present ...
		observers.remove(observer);
	}
	
	/**
	 * Metoda prazni listu observera na instancu
	 * klase na kojoj je pozvana.
	 */
	public void clearObservers() {
	// remove all observers from observers list ...
		observers.clear();
	}
	
	/**
	 * Metoda vraca vrijednost pohranjenu u instanci
	 * klase na kojoj je pozvana.
	 * @return vrijednost pohranjena u instanci.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Metoda postavlja vrijednost pohranjenu u klasi na
	 * proslijedenu vrijednost.
	 * Takoder, o promjeni obavjestava ste observere iz svoje 
	 * liste.
	 * @param value vrijednost na koju postavljamo.
	 */
	public void setValue(int value) {
		// Only if new value is different than the current value:
		IntegerStorageChange changeStorage = new IntegerStorageChange(this, this.value, value);
		if(this.value != value) {
			// Update current value
			this.value = value;// Notify all registered observers
			if(observers != null) {
				for(IntegerStorageObserver observer : observers) {
					observer.valueChanged(changeStorage);
				}
			}
		}
	}
}