package hr.fer.zemris.java.hw13.aplikacija2.trigonometry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Pomocni razred za racunanje trigonometrijskih vrijednosti.
 * Sadrzi metodu calculate koja izracunava sin i cos brojeva 
 * u zadanom intervalu(cijelih brojeva)
 * @author jelena
 *
 */
public class TrigonometryUtility {
	
	/**
	 * Instanca {@link DecimalFormat} koristena u ovom razredu
	 */
	private static final DecimalFormat fourDForm = new DecimalFormat("#.####"); 

	/**
	 * Metoda izracunava sinuse i kosinuse brojeva u rasponu startFrom i 
	 * endAt. ako startFrom veci, zamijenjuje ih, ako endAt veci startFrom+720,
	 * endAt je startFrom+720.
	 * oba kraja intervala su ukljucena.
	 * @param startFrom pocetni broj
	 * @param endAt zavrsni broj
	 * @return lista parova sinusa i kosinusa, lista objekata tipa {@link Pair}
	 */
	public static List<Pair> calculate(Integer startFrom, Integer endAt) {
		if(startFrom > endAt) {
			Integer tmp = startFrom;
			startFrom = endAt;
			endAt = tmp;
		}
		if(endAt > startFrom + 720) {
			endAt = startFrom + 720;
		}
		
		List<Pair> results = new ArrayList<Pair>();

		for(int i = startFrom, end = endAt; i <= end; ++i) {
			double radians = Math.toRadians(i);
			double sin = Math.sin(radians);
			double cos = Math.cos(radians);
		    sin = Double.valueOf(fourDForm.format(sin));
		    cos = Double.valueOf(fourDForm.format(cos));
			results.add(new Pair(sin, cos));
		}
		return results;
	}

	/**
	 * Razred sluzi za pohranu parova sin, cos za neki broj.
	 * @author jelena
	 *
	 */
	public static class Pair {
		
		/**
		 * Sinus broja
		 */
		private double sin;
		
		/**
		 * Kosinus broja
		 */
		private double cos;
		
		
		/**
		 * Konstruktor razreda.
		 * Postavlja clanske varijable na proslijedene vrijednosti.
		 * @param sin sinus broja
		 * @param cos kosinus broja
		 */
		public Pair(double sin, double cos) {
			super();
			this.sin = sin;
			this.cos = cos;
		}
		
		/**
		 * Metoda dohvaca vrijednost sinusa
		 * @return sinus broja pohranjen u this
		 */
		public double getSin() {
			return sin;
		}

		/**
		 * Metoda dohvaca vrijednost kosinusa
		 * @return kosinus broja pohranjen u this
		 */
		public double getCos() {
			return cos;
		}
		
		@Override
		public String toString() {
			return "(" + sin + "," + cos + ")";
		}
	}
}
