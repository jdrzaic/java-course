package hr.fer.zemris.java.tecaj.hw5.db.filter.operators;

/**
 * Razred sluzi za za rad s operatorom 'manje' u uvjetu iz upita na bazi.
 * Radi na uvjetima koji ne sadrze wildcard simbol *.
 * Implementirano je sucelje IComparisonOperator
 * @author Jelena Drzaic
 *
 */
public class LessCondition implements IComparisonOperator {

	@Override
	public boolean satisfied(String value1, String value2) {
		return value1.compareTo(value2) < 0;
	}

}
