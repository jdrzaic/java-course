package hr.fer.zemris.java.custom.scripting.tokens;

/**
 * Sucelje predstavlja visitor kao komponentu oblikovnog obrasca
 * Visitor. Sluzi za obavljanje operacija nad instancama {@link Token}.
 * Posao koji se obavlja zadan je konkretnim implementacijama sucelja.
 * @author jelena
 *
 */
public interface ITokenVisitor {

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenConstantDouble}.
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenConstantDouble posjecena instanca {@link TokenConstantDouble}
	 */
	public void visitConstantDouble(TokenConstantDouble tokenConstantDouble);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenConstantInteger}.
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenConstantInteger posjecena instanca {@link TokenConstantInteger}
	 */
	public void visitConstantInteger(TokenConstantInteger tokenConstantInteger);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenEquals}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenEquals posjecena instanca {@link TokenEquals}
	 */
	public void visitEquals(TokenEquals tokenEquals);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenFunction}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenFunction posjecena instanca {@link TokenFunction}
	 */
	public void visitFunction(TokenFunction tokenFunction);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenOperator}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenOperator posjecena instanca {@link TokenOperator}
	 */
	public void visitOperator(TokenOperator tokenOperator);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenSpecial}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenSpecial posjecena instanca {@link TokenSpecial}
	 */
	public void visitSpecial(TokenSpecial tokenSpecial);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenString}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenString posjecena instanca {@link TokenString}
	 */
	public void visitString(TokenString tokenString);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenText}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenText posjecena instanca {@link TokenText}
	 */
	public void visitText(TokenText tokenText);

	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TokenVariable}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param tokenVariable posjecena instanca {@link TokenVariable}
	 */
	public void visitVariable(TokenVariable tokenVariable);
	
}
