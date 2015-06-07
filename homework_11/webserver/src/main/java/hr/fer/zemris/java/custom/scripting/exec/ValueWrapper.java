package hr.fer.zemris.java.custom.scripting.exec;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

/**
 * Razred sluzi kao Wrapper objekata tipa Integer,
 * String ili Double.
 * Nudi aritmeticke operacije nad argumentima.
 * @author Jelena Drzaic
 *
 */
public class ValueWrapper {
	
	/**
	 * Pohranjeni objekt
	 */
	private Object value;

	/**
	 * {@link NumberFormatter} koristen za implementaciju metoda
	 */
	static NumberFormat formatter = NumberFormat.getInstance(Locale.US);
	
	/**
	 * Konsturktor razreda ValueWrapper.
	 * Pohranjuje objekt u instancu klase.
	 * Ako objekt nije dozvoljenog tipa, izbacuje RuntimeException.
	 * @param value vrijednost koju pohranjujemo.
	 */
	public ValueWrapper(Object value) {
		
		if(value == null) {
			this.value = new Integer(1);
		}else if((value instanceof String) || (value instanceof Integer) || 
				(value instanceof Double)) {
			this.value = value;
		}else {
			throw new RuntimeException("Objekt nije dozvoljenog tipa.");
		}
	}
	
	/**
	 * Metoda uvecava pohranjenu vrijednost za proslijedenu vrijednost.
	 * Ako je jedna od njih tipa Double, zbroj se pohranjuje kao Double,
	 * inace, kao Integer.
	 * @param incValue vrijednost za koju uvecavamo.
	 */
	public void increment(Object incValue) {
		
		Object value;
		
		this.value = parse(this.value);
		incValue = parse(incValue);
		
		if(prepare(this.value, incValue))  {
			if(incValue instanceof Double && this.value instanceof Double) {
				value = ((Double)this.value).doubleValue() + ((Double)incValue).doubleValue();
			}else if(incValue instanceof Integer) {
				value = ((Integer)incValue).intValue() + ((Double)this.value).doubleValue();
			}else {
				value = ((Integer)this.value).intValue() + ((Double)incValue).doubleValue();
			}
		}else {
			value = ((Integer)this.value).intValue() + ((Integer)incValue).intValue();
		}
		this.value = value;
	}

	/**
	 * Metoda umanjuje pohranjenu vrijednost za proslijedenu vrijednost.
	 * Ako je jedna od njih tipa Double, razlika se pohranjuje kao Double,
	 * inace, kao Integer.
	 * @param decValue vrijednost za koju umanjujemo.
	 */
	public void decrement(Object decValue) {
		
		Object value;
		
		this.value = parse(this.value);
		decValue = parse(decValue);
		
		if(prepare(this.value, decValue))  {
			if(decValue instanceof Double && this.value instanceof Double) {
				value = ((Double)this.value).doubleValue() - ((Double)decValue).doubleValue();
			}
			else if(decValue instanceof Integer) {
				value = -((Integer)decValue).intValue() + ((Double)this.value).doubleValue();
			}else {
				value = ((Integer)this.value).intValue() - ((Double)decValue).doubleValue();
			}
		}else {
			value = ((Integer)this.value).intValue() - ((Integer)decValue).intValue();
		}
		this.value = value;
	}
	
	/**
	 * Metoda mnozi pohranjenu vrijednost proslijedenom vrijednosti.
	 * Ako je jedna od njih tipa Double, umnozak se pohranjuje kao Double,
	 * inace, kao Integer.
	 * @param mulValue vrijednost kojom mnozimo.
	 */
	public void multiply(Object mulValue) {

		Object value;
		
		this.value = parse(this.value);
		mulValue = parse(mulValue);
		
		if(prepare(this.value, mulValue))  {
			if(mulValue instanceof Double && this.value instanceof Double) {
				value = ((Double)this.value).doubleValue() * ((Double)mulValue).doubleValue();
			}
			else if(mulValue instanceof Integer) {
				value = ((Integer)mulValue).intValue() * ((Double)this.value).doubleValue();
			}else {
				value = ((Integer)this.value).intValue() * ((Double)mulValue).doubleValue();
			}
		}else {
			value = ((Integer)this.value).intValue() * ((Integer)mulValue).intValue();
		}
		this.value = value;
	}
	
	/**
	 * Metoda dijeli pohranjenu vrijednost proslijedenom vrijednosti.
	 * Ako je jedna od njih tipa Double, kvocijent se pohranjuje kao Double,
	 * inace, kao Integer.
	 * @param divValue vrijednost kojom dijelimo.
	 */
	public void divide(Object divValue) {
		
		Object value;
		
		this.value = parse(this.value);
		divValue = parse(divValue);
		
		if(prepare(this.value, divValue))  {
			if(divValue instanceof Double && this.value instanceof Double) {
				value = ((Double)this.value).doubleValue() / ((Double)divValue).doubleValue();
			}
			else if(divValue instanceof Integer){
				value = ((Double)this.value).doubleValue() / ((Integer)divValue).intValue();
			}else {
				value = ((Integer)this.value).intValue() / ((Double)divValue).doubleValue();
			}
		}else {
			value = ((Integer)this.value).intValue() / ((Integer)divValue).intValue();
		}
		this.value = value;
	}

	/**
	 * Metoda sluzi za dohvat pohranjene vrijednosti.
	 * @return pohranjena vrijednost.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Metoda sluzi za postavljanje vrijednosti na proslijedenju vrijednost.
	 * @param value proslijedena vrijednost.
	 */
	public void setValue(Object value) {
		if(value instanceof String || value instanceof Double || value instanceof Integer) {
			this.value = value;
		}
		else {
			throw new RuntimeException("Objekt nije dozvoljenog tipa.");
		}
	}
	
	/**
	 * Metoda usporeduje pohranjenu vrijednost t proslijedenom vrijednosti.
	 * @param withValue proslijedena vrijednost s kojom usporedujemo.
	 * @return broj manji od 0 ako je pohranjena vrijednost manja od proslijedene, 
	 * 0 ako su jednake, broj veci od 0 inace.
	 */
	public int numCompare(Object withValue) {
		this.value = parse(this.value);
		withValue = parse(withValue);
		if(prepare(this.value, withValue)) {
			return ((Double)value).compareTo((Double)withValue);
		}else {
			return ((Integer)value).compareTo((Integer)withValue);
		}
	}
	
	/**
	 * Metoda vraca true ako je jedan od proslijedenih objekata Double,
	 * false inace.
	 * @param obj1 prvi proslijedeni objekt.
	 * @param obj2 drugi proslijedeni objekt.
	 * @return true ako je neki objekt Double, false inace.
	 */
	private boolean prepare(Object obj1, Object obj2){
		if(obj1 instanceof Double || obj2 instanceof Double) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metoda stvara broj, tipa Integer ili Double, iz proslijedene vrijednosti,
	 * ako je ona tipa String.
	 * Ako je objekt nekog drugog tipa, ne radi nista.
	 * @param obj Objekt koji parse-amo.
	 * @return Parse-ani objekt.
	 */
	private Object parse(Object obj){
		if(!(obj instanceof String)) {
			return obj;
		}
		String str = (String)obj;
		ParsePosition pos = new ParsePosition(0);
		Number number = formatter.parse(str, pos);
		if(pos.getIndex() != str.length()) {
			throw new RuntimeException("string ne moze biti interpretiran kao broj");
		}
		if(number instanceof Long) {
			number = new Integer(number.intValue());
		}
		return number;
	}
}
