package hr.fer.zemris.java.custom.collections;

/**
 * Razred implementira kolekciju elemenata tipa Object.
 * Dopustena je pohrana elemenata-duplikata, 
 * no nije dopustena pohrana null referenci.
 * @author Jelena Drzaic
 *
 */
public class ArrayBackedIndexedCollection {
	
	/**
	 * Defaultna velicina polja
	 */
	private static final int DEFAULT_SIZE = 16;
	/**
	 * broj elementata pohranjenih u polju elements
	 */
	private int size;
	
	/**
	 * kapacitet alociranog polja referenci na objekte
	 */
	private int capacity;
	
	/**
	 * polje referenci na objekte duljine capacity
	 */
	public Object[] elements;
	
	/**
	 * konstruktor razreda ArrayBackedIndexCollection
	 * Alocira polje elements velicine initialCapacity
	 * Ako je proslijedena vrijednost manja od jedan,
	 * program izbacuje iznimku
	 * @param initialCapacity velicina novoalociranog polja
	 */
	public ArrayBackedIndexedCollection(int initialCapacity) {
		if(initialCapacity < 1) {
			throw new IllegalArgumentException("initialCapacity mora biti veci od 0");
		}
		capacity = initialCapacity;
		elements = new Object[capacity];
		size = 0;
	}
	
	/**
	 * Defaultni konstruktor razreda ArrayBackedIndexCollection
	 */
	public ArrayBackedIndexedCollection() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * Provjerava je li polje elements prazno
	 * Slozenost metode je O(1)
	 * @return ako je this.elements prazno vraca true, inace false
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Metoda vraca velicinu polja elements
	 * slozenost metode je O(1)
	 * @return velicina objekta na kojem je pozvana
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Metoda dodaje element value na kraj instance razreda na kojem je pozvana
	 * Prosjecna slozenost metode je O(1)
	 * Ako je value jednak null, metoda izbacuje iznimku
	 * @param value element koji se dodaje u objekt
	 */
	public void add(Object value) {
		this.insert(value, size);
	}
	
	/**
	 * Metoda vraca Objekt na poziciji index u objektu na kojem je pozvana  
	 * Slozenost metode je O(1)
	 * ako je index nepostojeca pozicija metoda izbacuje iznimku
	 * @param index pozicija elementa kojeg dohvacamo
	 * @return Objekt na poziciji index
	 */
	public Object get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index is not regular");
		}
		return elements[index];
	}
	
	/**
	 * Izbacuje element na poziciji index iz objekta na kojem je pozvana
	 * Slozenost metode je O(N)
	 * @param index pozicija Objekta
	 */
	public void remove(int index) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		for(int position = index + 1; position < size; ++position) {
			elements[position - 1] = elements[position];
		}
		--size;
	}
	
	/**
	 * Metoda ubacuje objekt value na poziciju index
	 * Slozenost metode je O(n) 
	 * Ako je proslijedena vrijednost value jednaka null, 
	 * program izbacuje iznimku
	 * Ako je proslijedeni index nepostojeca pozicija,
	 * metoda izbacuje iznimku 
	 * @param value objekt koji ubacujemo
	 * @param index pozicija na koju ubacujemo
	 */
	public void insert(Object value, int index) {
		if(value == null) {
			throw new IllegalArgumentException("null value is not allowed");
		}
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index is not regular");
		}
		if(size == capacity) {
			Object[] temporary = new Object[size * 2];
			//System.arraycopy(elements, 0, temporary, 0, size);
			for(int i = 0; i < size; ++i) {
				temporary[i] = elements[i];
			}
			elements = temporary;
			capacity *= 2;
		}
		for(int position = size - 1; position >= index; --position) {
			elements[position + 1] = elements[position];
		}
		elements[index] = value;
		++size;
	}
	
	/**
	 * Metoda vraca prvi indeks na kojem se pojavljuje objekt vrijednosti
	 * value
	 * Slozenost metode je O(n)
	 * @param value objekt ciju poziciju potrazujemo
	 * @return pozicija prvog pojavljivanja objekta value
	 */
	public int indexOf(Object value) {
		for(int position = 0; position < size; ++position) {
			if(elements[position].equals(value)) {
				return position;
			}
		}
		return -1;
	}
	
	/**
	 * Metoda pomocu koje doznajemo postoji li objekt value 
	 * unutar instance razreda na kojoj je pozvana
	 * Slozenost metode je O(n)
	 * @param value objekt kojeg trazimo
	 * @return true ako je objekt value pronaden, false inace
	 */
	public boolean contains(Object value) {
		if(indexOf(value) == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metoda postavlja velicinu instance klase na kojoj je
	 * pozvana na nulu
	 * Slozenost metode je O(n)
	 */
	public void clear() {
		size = 0;
		for(int position = 0; position < capacity; ++position) {
			elements[position] = null;
		} 
	}
}
