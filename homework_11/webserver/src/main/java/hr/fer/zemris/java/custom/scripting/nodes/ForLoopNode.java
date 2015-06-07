package hr.fer.zemris.java.custom.scripting.nodes;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.tokens.*;

/**
 * Razred naslijeden od razreda Node
 * Razred reprezentira jednu for petlju 
 * @author Jelena Drzaic
 */
public class ForLoopNode extends Node {
	/**
	 * varijabla tipa TokenVariable
	 */
	private TokenVariable variable;
	
	/**
	 * varijabla tipa Token-pocetni token u izrazu
	 */
	private Token startExpression;
	
	/**
	 * varijabla tipa Token-zavrsni token u izrazu
	 */
	private Token endExpression;
	
	/**
	 * varijabla tipa Token-srednji token izraza, moze biti null
	 */
	private Token stepExpression;
	
	/**
	 * Konstruktor razreda ForLoopNode
	 * @param variable vrijednost na koju inicijaliziramo this.variable
	 * @param start vrijednost na koju inicijaliziramo startExpression
	 * @param end vrijednost na koju inicijaliziramo endExpression
	 * @param step vrijednost na koju inicijaliziramo stepExpression
	 */
	public ForLoopNode(TokenVariable variable, Token start, Token end, Token step) {
		this.variable = variable;
		startExpression = start;
		endExpression = end;
		stepExpression = step;
	}
	
	/**
	 * Metoda vraca vrijednost varijable variable
	 * @return this.variable
	 */
	public TokenVariable getVariable() {
		return variable;
	}
	
	/**
	 * Metoda vraca vrijednost varijable startExpression
	 * @return this.startExpression
	 */
	public Token getStartExpression() {
		return startExpression;
	}
	
	/**
	 * Metoda vraca vrijednost varijable endExpression
	 * @return this.endExpression
	 */
	public Token getEndExpression() {
		return endExpression;
	}
	
	/**
	 * Metoda vraca vrijednost varijable stepExpression
	 * @return this.stepExpression
	 */
	public Token getStepExpression() {
		return stepExpression;
	}
	
	/**
	 * Metoda vraca escapiranu verziju Stringa pohranjenog u 
	 * instanci razreda
	 * Metoda poziva analognu funkciju za Nodeove u polju children
	 * @return escapirani String
	 */
	@Override
	public String toString() {
		String out = "{$FOR " + variable.toString() + " " + 
					 startExpression.toString() + " " +
					 endExpression.toString();
		if (stepExpression != null) {
			out += " " + stepExpression.toString();
		}
		out += "$}";
		for (int i = 0; i < numberOfChildren(); ++i) {
			out += getChild(i).toString();
		}
		return out + "{$END$}";
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitForLoopNode(this);
	}
}
