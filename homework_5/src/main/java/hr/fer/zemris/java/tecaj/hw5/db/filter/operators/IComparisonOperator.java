package hr.fer.zemris.java.tecaj.hw5.db.filter.operators;

/**
 * Sucelje sluzi za rad s operatorima u uvjetima iz
 * upita na bazi.
 * @author Jelena Drzaic
 *
 */
public interface IComparisonOperator {
	
	/**
	 * Metoda provjerava zadovoljavaju li stringovi 
	 * uvjete predvidene definicijom operatora.
	 * Metoda vraca true ako je uvjet zadovoljen, false inace
	 * @param value1 string koji sadrzi prvu vrijednost
	 * @param value2 string koji sadrzi drugu vrijednost
	 * @return true, ako su stringovi u dobrom odnosu,
	 * false inace.
	 */
	public boolean satisfied(String value1, String value2);
}
