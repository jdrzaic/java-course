package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Memory;

/**
 * Razred implementira sucelje Memory.
 * 
 * @author Jelena Drzaic
 *
 */
public class MemoryImpl implements Memory{

	/**
	 * velicina memorije
	 */
	private int size;
	
	/**
	 * Polje objekata pohranjenih u memoriji
	 */
	private Object[] objects;
	
	/**
	 * @param size
	 */
	
	/**
	 * Konstruktor razreda Memory. 
	 * Konstruira memoriju zeljene velicine.
	 * Ako je proslijedena velicina manja ili jednaka 0, izbacuje 
	 * IllegalArgumentException.
	 * @param size zeljena velicina memorije.
	 */
	public MemoryImpl(int size) {
		if(size <= 0) {
			throw new IllegalArgumentException("velicina memorije mora biti "
					+ "veca od 0.");
		}
		this.objects = new Object[size];
		for(@SuppressWarnings("unused") Object object: objects) {
			object = null;
		}
		this.size = size;
	}
	@Override
	public void setLocation(int location, Object value) {
		if(location < 0 || location >= size) {
			throw new IndexOutOfBoundsException("lokacija " + location + "nije legalna.");
		}
		objects[location] = value;
	}

	@Override
	public Object getLocation(int location) {
		if(location < 0 || location >= size) {
			throw new IndexOutOfBoundsException("lokacija " + location + "nije legalna.");
		}
		return objects[location];
	}
}
