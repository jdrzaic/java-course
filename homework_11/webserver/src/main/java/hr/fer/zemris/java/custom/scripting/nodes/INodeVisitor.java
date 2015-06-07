package hr.fer.zemris.java.custom.scripting.nodes;

/**
 * Sucelje predstavlja visitor kao komponentu oblikovnog obrasca
 * Visitor. Sluzi za obavljanje operacija nad instancama {@link Node}.
 * Posao koji se obavlja zadan je konkretnim implementacijama sucelja.
 * @author jelena
 *
 */
public interface INodeVisitor {
	
	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link TextNode}. 
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param node posjecena instanca {@link TextNode}
	 */
	public void visitTextNode(TextNode node);
	
	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link ForLoopNode}
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param node posjecena instanca {@link ForLoopNode}
	 */
	public void visitForLoopNode(ForLoopNode node);
	
	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link EchoNode}.
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param node posjecena instanca {@link EchoNode}
	 */
	public void visitEchoNode(EchoNode node);
	
	/**
	 * Posao koji se odraduje prilikom dolaska na 
	 * {@link DocumentNode}.
	 * Posao je odreden konkretnim implementacijama sucelja.
	 * @param node posjecena instanca {@link DocumentNode}
	 */
	public void visitDocumentNode(DocumentNode node);
}
