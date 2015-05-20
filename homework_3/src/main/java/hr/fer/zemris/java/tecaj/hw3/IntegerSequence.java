package hr.fer.zemris.java.tecaj.hw3;
import java.util.Iterator;

/**
 * Razred implementira sucelje Iterable.
 * @author Jelena Drzaic
 *
 */
public class IntegerSequence implements Iterable<Integer>{
	/**
	 * Pocetak intervala iteriranja
	 */
	private int pocetak;
	
	/**
	 * kraj intervala iteriranja
	 */
	private int kraj;
	
	/**
	 * korak iteracije
	 */
	private int korak;
	
	/**
	 * Konstruktor razreda Iterable.
	 * @param pocetak pocetak intervala iteriranja
	 * @param kraj kraj intervala iteriranja
	 * @param korak korak iteracije
	 */
	public IntegerSequence(final int pocetak, final int kraj, final int korak) {
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.korak = korak;
	}
	
	/**
	 * Metoda stvara novi Iterator.
	 * @return novi iterator
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new IntegerIterator();
	}
	
	/**
	 * Razred implementira sucelje Iterator<Integer>
	 * @author Jelena Drzaic
	 *
	 */
	private class IntegerIterator implements Iterator<Integer> {
		
		/**
		 * trenutni polozaj iteratora
		 */
		private int trenutni;
		
		/**
		 * Konstruktor razreda.
		 * postavlja iterator na pocetak intervala iteracije
		 */
		public IntegerIterator() {
			trenutni = pocetak;
		}
		
		/**
		 * Metoda provjerava postoji li sljedeci po redu iterator.
		 * @return true, ako postoji, false inace.
		 */
		@Override
		public boolean hasNext() {
			//System.out.println(trenutni);
			return trenutni + korak <= kraj + korak;
		}
		
		/**
		 * Metoda vraca sljedeci polozaj iteratora.
		 * @return pozicija iteratora
		 */
		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new IllegalArgumentException("No more elements.");
			}
			int trenutniStari = trenutni;
			trenutni += korak;
			return trenutniStari;
		}
		
		@Override
		public void remove() {
			throw new IllegalArgumentException("Request invalid");
		}
	}
}
