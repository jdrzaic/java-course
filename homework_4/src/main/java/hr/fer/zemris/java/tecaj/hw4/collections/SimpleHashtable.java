package hr.fer.zemris.java.tecaj.hw4.collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Razred predstavlja tablicu rasprsenog adresiranja koja omogucava pohranu
 * uredenih parova(kljuc, vrijednost).
 * Razred implementira i sucelje Iterable, koje sluzi za iteriranje po hash tablici.
 * @author Jelena Drzaic
 */
public class SimpleHashtable implements Iterable<SimpleHashtable.TableEntry>{
	
	/**
	 * pocetna velicina spremnika,
	 * kod pozivanja praznog konstruktora.
	 */
	final static int DEFAULT = 16;
	
	/**
	 * broj trenutno pohranjenih parova.
	 */
	private int size;
	
	/**
	 * trenutni kapacitet tablice, tj. broj pretinaca.
	 */
	private int capacity;
	
	/**
	 * hash tablica; spremnik objekata tipa TableEntry
	 */
	private TableEntry[] table;
	
	private int modificationCount;
	/**
	 * Konstruktor razreda SimpleHashtable.
	 * Stvara praznu hash tablicu s 16 pretinaca.
	 */
	public SimpleHashtable() {
		table = new TableEntry[DEFAULT];
		size = 0;
		capacity = DEFAULT;
		modificationCount = 0;
	}
	
	/**
	 * Konstruktor razreda SimpleHashtable.
	 * Kao argument prima zeljeni broj pretinaca u tablici.
	 * @param size zeljeni broj pretinaca.
	 */
	public SimpleHashtable(int size) {
		int newSize = 1;
		while(newSize < size) {
			newSize *= 2;
		}
		table = new TableEntry[newSize];
		size = 0;
		capacity = newSize;
		modificationCount = 0;
	}
	
	/**
	 * Metoda vraca redni broj pretinca u kojeg treba smjestiti objekt s 
	 * vrijednoscu kljuca key.
	 * @param key vrijednost kljuca ciji pretinac trazimo.
	 * @return pretinac za pohranu objekta.
	 */
	private int getSlot(Object key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	/**
	 * Metoda ubacuje par (key, value) u hash tablicu.
	 * @param key kljuc objekta koji spremamo.
	 * @param value vrijednost objekta koji spremamo.
	 */
	public void put(Object key, Object value) {
		
		if(key == null) {
			throw new IllegalArgumentException("key cannot be null.");
		}
		++modificationCount;
		//kontrola popunjenosti
		if((double)size/capacity >= 0.75) {
			resizeTable();
		}
		int slot = getSlot(key);
		//pretinac u koji pohranjujemo je prazan
		if(table[slot] == null) {
			table[slot] = new TableEntry(key, value, null);
			++size;
			return;
		}
		//na pocetku pretinca je objekt s kljucem key
		TableEntry current = table[slot];
		if((current.getKey()).equals(key)) {
			current.setValue(value);
			return;
		}
		while(current.next != null) {
			if((current.next.getKey()).equals(key)) {
				current.next.setValue(value);
				return;
			}
			current = current.next;
		}
		current.next = new TableEntry(key, value, null);
		++size;
	}
	
	/**
	 * Metoda vraca kapacitet tablice
	 * @return kapacitet
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Metoda vraca drugi parametar para (key, value)-vrijednost varijable value.
	 * @param key kljuc objekta ciju vrijednost trazimo.
	 * @return objekt pohranjen u varijabli value u paru s kljucem key.
	 */
	public Object get(Object key) {
		TableEntry current = table[getSlot(key)];
		while(current != null) {
			if(current.getKey().equals(key)) {
				return current.getValue();
			}
			current = current.next;
		}
		return null;
	}
	
	/**
	 * Metoda vraca broj parova pohranjenih u hash tablici.
	 * @return broj parova.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Metoda provjerava nalazi li se u hash tablici objekt s vrijednoscu kljuca key.
	 * Metoda vraca true ako objekt postoji, false inace.
	 * @param key kljuc po kojem trazimo objekt.
	 * @return true ako je objekt s kljucem key pronaden, false inace.
	 */
	public boolean containsKey(Object key) {
		TableEntry current = table[getSlot(key)];
		while(current != null) {
			if(current.getKey().equals(key)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	 * Metoda provjerava postoji li u hash tablici objekt s pohranjenom vrijednosti value.
	 * Ako objekt postoji, vraca true, inace false.
	 * @param value vrijednost prema kojoj trazimo objekt.
	 * @return true ako je objekt pronaden, false inace.
	 */
	public boolean containsValue(Object value) {
		TableEntry current;
		for(int slot = 0; slot < capacity; ++slot) {
			current = table[slot];
			while(current != null) {
				if(current.getValue().equals(value)) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}
	
	/**
	 * Metoda uklanja objekt s kljucem vrijednosti key iz hash tablice.
	 * Ako takav objekt ne postoji, tablica ostaje nepromijenjena.
	 * @param key kljuc objekta kojeg brisemo.
	 */
	public void remove(Object key) {
		
		TableEntry current = table[getSlot(key)];
		if(current == null) return;
		if(current.getKey().equals(key)) {
			table[getSlot(key)] = current.next;
			--size;
			++modificationCount;
			return;
		}
		while(current.next != null) {
			if(current.next.getKey().equals(key)) {
				current.next = current.next.next;
				--size;
				++modificationCount;
				return;
			}
			current = current.next;
		}
	}
	
	/**
	 * Metoda provjerava je li tablica prazna.
	 * @return true ako je tablica prazna, false inace.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Metoda vraca string reprezentaciju hash tablice.
	 * String je oblika "[key1=value1, ....,keyn=valuen]"
	 * @return String reprezentacija tablice.
	 */
	@Override
	public String toString() {
		
		boolean foundEntry = false;
		StringBuilder newString = new StringBuilder(16);
		newString.append("[");
		TableEntry current;
		for(int slot = 0; slot < capacity; ++slot) {
			current = table[slot];
			while(current != null) {
				//prije svakog, osim prvog elementa liste, ispisujemo ', '
				if(foundEntry) {
					newString.append(", ");
				}else {
					foundEntry = true;
				}
				newString.append(current.toString());
				current = current.next;
			}
		}
		newString.append("]");
		return newString.toString();
	}
	
	/**
	 * Metoda udvostrucuje kapacitet tablice, tj. broj pretinaca.
	 * 
	 */
	private void resizeTable() {
		++modificationCount;
		int oldCapacity = capacity;
		capacity = 2 * capacity;
		TableEntry[] tmpTable = new TableEntry[2 * oldCapacity];
		tmpTable = table;
		table = new TableEntry[capacity];
		//tablicu punimo ispočetka
		size = 0;
		TableEntry current;
		for(int slot = 0; slot < oldCapacity; ++slot) {
			current = tmpTable[slot];
			while(current != null) {
				put(current.getKey(), current.getValue());
				current = current.next;
			}
		}
	}
	
	/**
	 * Metoda prazni hash tablicu, tj. iz nje uklanja sve pohranjene parove.
	 */
	public void clear() {
		++modificationCount;
		for(int slot = 0; slot < capacity; ++slot) {
			table[slot] = null;
			size = 0;
		}
	}
	
	/**
	 * Metoda implementira metodu sucelja Iterable.
	 * Metoda vraca novokonstruirani iterator.
	 */
	@Override
	public Iterator<SimpleHashtable.TableEntry> iterator() {
		return new IteratorImpl();
	}
	
	/**
	 * Razred implementira sucelje Iterator.
	 * @author Jelena Drzaic
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry> {
		
		/**
		 * trenutni objekt
		 */
		TableEntry next;
		TableEntry current;
		/**
		 * trenutni pretinac-pretinac objekta current.
		 */
		int currentSlot;
		
		int iterModificationCount;
		/**
		 * Konstruktor razreda.
		 * vraca iterator na pocetak hash tablice.
		 * Ako je tablica prazna, iterator je null.
		 */
		public IteratorImpl() {
			current = null;
			iterModificationCount = modificationCount;
			if(size == 0) {
				next = null;
			}
			for(int slot = 0; slot < capacity; ++slot) {
				if(table[slot] != null) {
					next = table[slot];
					currentSlot = slot;
					return;
				}
			}
		}
		
		/**
		 * Metoda provjerava postoji li sljedeci iterator u hash tablici.
		 */
		@Override
		public boolean hasNext() {
			if(modificationCount != iterModificationCount) {
				throw new ConcurrentModificationException("invalid iterator");
			}
			return next != null;
		}
		
		/**
		 * Metoda vraca sljedeci iterator, ako takav postoji.
		 * U suprotnom, izbacuje NoSuchElementException
		 */
		@Override
		public SimpleHashtable.TableEntry next() {
			if(modificationCount != iterModificationCount) {
				throw new ConcurrentModificationException("invalid iterator");
			}
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			current = next;
			next = next.next;
			if(next == null) {
				for(int slot  = currentSlot + 1; slot < capacity; ++slot) {
					if(table[slot] != null) {
						next = table[slot];
						currentSlot = slot;
					}
				}
			}
			return current;
		}
		
	
		
		/**
		 * Metoda izbacuje objekt na trenutnoj poziciji iteratora iz tablice.
		 */
		@Override
		public void remove() {
			if(modificationCount != iterModificationCount) {
				throw new ConcurrentModificationException("invalid iterator");
			}
			if(current == null) {
				throw new IllegalStateException("invalid iterator");
			}
			SimpleHashtable.this.remove(current.getKey());
			current = null;
			++iterModificationCount;
		}
	}
	
	/**
	 * Razred predstavlja jedan element pospremljen u hash tablici.
	 * Pomocu njega modeliramo razred SimpleHashtable.
	 * @author Jelena Drzaic
	 *
	 */
	public static class TableEntry {
		
		/**
		 * Vrijednost pohranjena u objektu
		 */
		private Object value;
		
		/**
		 * kljuc po kojem spremamo objekt.
		 */
		private Object key;
		
		/**
		 * Referenca na sljedeci objekt u vezanoj listi
		 */
		public TableEntry next;
		
		/**
		 * Konstruktor razreda TableEntry.
		 * @param key kljuc objekta.
		 * @param value vrijednost pohranjena u objektu.
		 * @param entry sljedeci element u vezanoj listi.
		 */
		public TableEntry(Object key, Object value, TableEntry entry) {
			this.value = value;
			this.key = key;
			this.next = entry;
		}

		/**
		 * Metoda vraca vrijednost zapisana u objektu.
		 * @return vrijednost objekta.
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Metoda postavlja vrijednost pohranjenu u objektu na zeljenu vrijednost.
		 * @param value
		 */
		public void setValue(Object value) {
			this.value = value;
		}

		/**
		 * Metoda vraca kljuc objekta na kojem je pozvana.
		 * @return kljuc objekta.
		 */
		public Object getKey() {
			return key;
		}
		
		/**
		 * Metoda vraca string interpretaciju objekta.
		 * String je oblika "key=value".
		 */
		@Override
		public String toString() {
			return "" + key + "=" + value;
		}	
	}
}
