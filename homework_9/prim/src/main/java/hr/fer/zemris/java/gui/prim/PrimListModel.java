package hr.fer.zemris.java.gui.prim;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Razred implementira sucelje ListModel.
 * Sluzi za pohranu brojeva tipa Integer.
 * Uz overridane metode, dana je i metoda next()
 * Pozivanjem te metode u listu se ubacuje sljedeci po 
 * velicini prosti broj.
 * @author jelena
 *
 */
public class PrimListModel implements  ListModel<Integer>{
	
	/**
	 * Lista brojeva
	 */
	List<Integer> primNumbers;
	
	/**
	 * Lista listenera
	 */
	List<ListDataListener> listeners;
	
	/**
	 * Konstruktor razreda.
	 * Inicijalizira clanske varijable,
	 * te u listu brojeva ubacuje broj 1.
	 */
	public PrimListModel() {
		primNumbers = new ArrayList<Integer>();
		primNumbers.add(1);
		listeners =  new ArrayList<ListDataListener>();
	}
	
	@Override
	public int getSize() {
		return primNumbers.size();
	}

	@Override
	public Integer getElementAt(int index) {
		return	primNumbers.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}
	
	/**
	 * Metoda u listu brojeva ubacuje prvi prosti broj veci od zadnjeg
	 * ubacenog broja.
	 */
	public void next() {
		PrimNumber p = new PrimNumber(primNumbers.get(primNumbers.size() - 1));
		primNumbers.add(p.getNext());
		for(int i = 0; i < listeners.size(); ++i) {
			listeners.get(i).intervalAdded(new ListDataEvent(this,
					ListDataEvent.INTERVAL_ADDED,  0, 0));
		}
	}
	
	/**
	 * Razred implementira dohvat prostih brojeva
	 * vecih od proslijedenog broja
	 * @author jelena
	 *
	 */
	private static class PrimNumber {
		
		/**
		 * Prost broj
		 */
		int p;
		
		/**
		 * Konstruktor razreda.
		 * @param p prost broj
		 */
		public PrimNumber(int p) {
			this.p = p;
		}
		
		/**
		 * Metoda vraca prvi veci prosti broj od broja proslijedenog u kostruktoru 
		 * razreda.
		 * @return prost broj
		 */
		public int getNext() {
			int i = p + 1;
			while(true) {
				if(isPrim(i)) {
					return i;
				}
				++i;
			}
		}
		
		/**
		 * Metoda za proslijedeni broj provjerava je li prost
		 * @param k
		 * @return
		 */
		private static boolean isPrim(int k) {
			for(int i = 2; i <= Math.sqrt(k); ++i) {
				if(k % i == 0) return false;
			}
			return true;
		}
	}
}
