package hr.fer.zemris.java.tecaj.hw5.db.filter.operators;

/**
 * Razred sluzi za za rad s operatorom 'jednako' u uvjetu iz upita na bazi.
 * Radi na uvjetima koji sadrze wildcard simbol *.
 * Implementirano je sucelje IComparisonOperator
 * @author Jelena Drzaic
 *
 */
public class WildCardEqualsCondition implements IComparisonOperator {

	@Override
	public boolean satisfied(String value1, String value2) {
		if(value2.indexOf('*') != value2.lastIndexOf('*')) {
			throw  new IllegalArgumentException("more than one wildcard");
		}
		//bilo koji string
		if(value2.equals("*")) {
			return true;
		}
		//uvjeti tipa "V*"
		if(value2.charAt(value2.length() - 1) == '*') {
			if(value1.startsWith(value2.substring(0, value2.length() - 1))) {
				return true;
			}else {
				return false;
			}
		}
		String[] components = value2.split("\\*");
		//nema "srednjeg" dijela
		if(value1.length() < components[0].length() + 
				(components.length > 1 ? components[1].length() : 0)) {
			return false;
		}
		if(value1.startsWith(components.length > 1 ? components[0] : "") 
			&& value1.endsWith(components.length > 1 ? components[1] : "")) {
			return true;
		}
		return false;
	}
}
