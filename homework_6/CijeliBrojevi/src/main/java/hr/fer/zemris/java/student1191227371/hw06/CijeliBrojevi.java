package hr.fer.zemris.java.student1191227371.hw06;

/**
 * Razred implementira provjere osnovnih svojstava cijelih brojeva.
 * Provjerava se parnost te prostost brojeva.
 * @author Jelena Drzaic
 *
 */
public class CijeliBrojevi {
	
	/**
	 * Metoda provjerava je li dani broj neparan.
	 * Ako jest, vraca true, inace vraca false.
	 * @param broj broj kojeg provjeravamo.
	 * @return true ako je broj neparan, false inace.
	 */
	public boolean jeNeparan(int broj) {
		return broj % 2 == 1;
	}
	
	/**
	 * Metoda provjerava je li dani broj paran.
	 * Ako jest, vraca true, inace vraca false.
	 * @param broj broj kojeg provjeravamo.
	 * @return true ako je broj paran, false inace.
	 */
    public boolean jeParan(int broj) {
    	return broj % 2 == 0;
    }
    
    /**
	 * Metoda provjerava je li dani broj prost.
	 * Ako jest, vraca true, inace vraca false.
	 * @param broj broj kojeg provjeravamo.
	 * @return true ako je broj prost, false inace.
	 */
    public boolean jeProst(int broj) {
    	for(int i = 2; i * i <= broj; ++i) {
    		if(broj % i == 0) {
    			return false;
    		}
    	}
    	return true;
    }
} 
