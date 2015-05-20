package hr.fer.zemris.java.tecaj.hw5.db.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import hr.fer.zemris.java.tecaj.hw5.db.components.StudentRecord;
import hr.fer.zemris.java.tecaj.hw5.db.filter.getter.*;
import hr.fer.zemris.java.tecaj.hw5.db.filter.operators.*;

/**
 * Razred implementira filtriranje podataka iz baze na temelju
 * upita na istoj.
 * Implementira sucelje IFilter.
 * @author Jelena Drzaic
 *
 */
public class QueryFilter implements IFilter {
	
	/**
	 * upit o zapisima iz baze
	 */
	String query;
	
	/**
	 * Lista uvjeta iz upita
	 */
	List<ConditionalExpression> expressions;
	
	/**
	 * jmbag trazen u upitu(ako postoji)
	 */
	String jmbag = null;
	
	/**
	 * Konstruktor razreda.
	 * Kao parametar prima string s upitom na bazi podataka.
	 * @param query String koji sadrzi upit na bazi.
	 */
	public QueryFilter(String query) {
		this.query = query;
	}
	
	/**
	 * Metoda razbija upit na bazi na pojedinacne uvjete.
	 * @return polje stringova-uvjeta dobivenih iz upita.
	 */
	private String[] parseQuery() {
		query = query.trim();
		String[] conditional = query.split("\\s+and\\s+"); 
		return conditional;
	}

	/**
	 * Metoda konstruira varijablu tipa ConditionalExpression
	 * iz stringa proslijedenog metodi, u kojoj je zapisan uvjet iz upita.
	 * @param conditional string s uvjetom
	 * @return varijabla tipa ConditionalExpression, uvjet u upitu na bazi.
	 */
	private ConditionalExpression constructExpression(String conditional) throws IllegalArgumentException {
		Pattern pattern = Pattern.compile("(?<keyWord>\\w+)(?<operator><|>|=|>=|<=)"
				+ "\"(?<word>.+)\"");
		Matcher matcher = pattern.matcher(conditional);
		if(!(matcher.matches())) {
			throw new IllegalArgumentException("Invalid conditional " + conditional);
		}
		IFieldValueGetter getter;
		IComparisonOperator comparisonOperator;
		//polje u zapisu baze
		String key = matcher.group("keyWord");
		
		String operator = matcher.group("operator");
		
		//trazeni string iz uvjeta
		String word = matcher.group("word");
		
		if(key.equals("lastName")) {
			getter = new LastNameFieldGetter();
		}else if(key.equals("firstName")) {
			getter = new FirstNameFieldGetter();
		}else if(key.equals("jmbag")) {
			//za trazenje po jmbag-u
			if(operator.equals("=")) {
				jmbag = word;
			}
			getter = new JMBAGFieldGetter();
		}else {
			throw new IllegalArgumentException("invalid key " + key);
		}
		
		//Wildcard u uvjetu
		if(word.contains("*")) {
			if(operator.equals("=")) {
				comparisonOperator = new WildCardEqualsCondition();
			}else {
				throw new IllegalArgumentException("invalid operator "+ operator);
			}
			//bez wildcarda
		}else {
			if(operator.equals("=")) {
				comparisonOperator = new EqualsCondition();
			}else if(operator.equals("<")) {
				comparisonOperator = new LessCondition();
			}else if(operator.equals("<=")) {
				comparisonOperator = new LessOrEqualCondition();
			}else if(operator.equals(">")) {
				comparisonOperator = new GreaterCondition();
			}else {
				comparisonOperator = new GreaterOrEqualCondition();
			}
		}
		return new ConditionalExpression(getter, word, comparisonOperator);
	}
	
	/**
	 * Metoda konstruira listu uvjeta dobivenih iz jednog query upita
	 * na bazi podataka.
	 */
	private void constructExpressions() {
		String[] conditionals = parseQuery();
		expressions = new ArrayList<ConditionalExpression>(conditionals.length);
		for(int i = 0, granica = conditionals.length; i < granica; ++i) {
			expressions.add(constructExpression(conditionals[i]));
		}
	}
	
	@Override
	public boolean accepts(StudentRecord record) {
		constructExpressions();
		boolean satisfies = true;
		for(int i = 0, granica = expressions.size(); i < granica; ++i) {
			satisfies = expressions.get(i).getOperator().satisfied(
					expressions.get(i).getGetter().get(record), 
					expressions.get(i).getStringLiteral());
			if(satisfies == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metoda vraca string-JMBAG iz upita, ako postoji.
	 * @return string u kojem je zapisan trazeni JMBAG.
	 */
	public String/* Optional<String>*/ getJMBAG() {
		return jmbag;
	}
}
