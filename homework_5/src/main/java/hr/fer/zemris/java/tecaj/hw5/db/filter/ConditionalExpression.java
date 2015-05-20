package hr.fer.zemris.java.tecaj.hw5.db.filter;

import hr.fer.zemris.java.tecaj.hw5.db.filter.getter.IFieldValueGetter;
import hr.fer.zemris.java.tecaj.hw5.db.filter.operators.IComparisonOperator;

/**
 * Metoda reprezentira jedan kondicional-uvjet u upitu na bazi
 * podataka.
 * @author Jelena Drzaic
 *
 */
public class ConditionalExpression {
	
	/**
	 * Informacija o varijabli zapisa u bazi koju promatramo
	 */
	IFieldValueGetter getter;
	
	/**
	 * Trazeni string
	 */
	String stringLiteral;
	
	/**
	 * Operator koristen u zapisu iz upita
	 */
	IComparisonOperator operator;
	
	/**
	 * Konstruktor razreda.
	 * @param getter informacija o varijabli u zapisu baze 
	 * @param wanted string s kojim usporedujemo zapise baze
	 * @param operator operator koristen u upitu.
	 */
	public ConditionalExpression(IFieldValueGetter getter,
			String wanted, IComparisonOperator operator) {
		this.getter = getter;
		this.stringLiteral = wanted;
		this.operator = operator;
	}

	/**
	 * Metoda vraca informaciju o varijabli u zapisu baze
	 * koristenoj u uvjetu upita.
	 * @return varijabla u zapisu baze, tipa IFieldValueGetter.
	 */
	public IFieldValueGetter getGetter() {
		return getter;
	}

	/**
	 * Metoda vraca string s kojim usporedujemo podatke iz baze,
	 * a koristen je u uvjetu upita.
	 * @return string s kojim usporedujemo podatke iz baze.
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}

	/**
	 * Operator koristen u uvjetu upita.
	 * @return operator u uvjetu.
	 */
	public IComparisonOperator getOperator() {
		return operator;
	}
	
	
}
