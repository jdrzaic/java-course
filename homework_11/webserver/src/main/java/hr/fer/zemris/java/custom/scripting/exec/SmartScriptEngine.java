package hr.fer.zemris.java.custom.scripting.exec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import hr.fer.zemris.java.custom.scripting.exec.functions.Functions;
import hr.fer.zemris.java.custom.scripting.exec.functions.IFunction;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.INodeVisitor;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.tokens.ITokenVisitor;
import hr.fer.zemris.java.custom.scripting.tokens.Token;
import hr.fer.zemris.java.custom.scripting.tokens.TokenConstantDouble;
import hr.fer.zemris.java.custom.scripting.tokens.TokenConstantInteger;
import hr.fer.zemris.java.custom.scripting.tokens.TokenEquals;
import hr.fer.zemris.java.custom.scripting.tokens.TokenFunction;
import hr.fer.zemris.java.custom.scripting.tokens.TokenOperator;
import hr.fer.zemris.java.custom.scripting.tokens.TokenSpecial;
import hr.fer.zemris.java.custom.scripting.tokens.TokenString;
import hr.fer.zemris.java.custom.scripting.tokens.TokenText;
import hr.fer.zemris.java.custom.scripting.tokens.TokenVariable;
import hr.fer.zemris.java.webserver.RequestContext;


/**
 * Razred implementira izvrsavanje programa dobivenog kao parsirano stablo
 * stvorenog iz tekstualnog file-a. 
 * Dokument je parsiran razredom {@link SmartScriptParser}.
 * 
 * @author jelena
 *
 */
public class SmartScriptEngine {
	
	/**
	 * Podaci, parsirano stablo dokumenta kojeg izvrsavamo
	 */
	private DocumentNode documentNode;
	
	/**
	 * Instanca razreda {@link RequestContext}
	 * Izvrseni program ispisujemo na njen output stream
	 */
	private RequestContext requestContext;
	
	/**
	 * Stack koristen kod izvrsavanja programa
	 */
	private ObjectMultistack multistack = new ObjectMultistack();
	
	/**
	 * Instanca implementacije sucelja {@link INodeVisitor}
	 * Sluzi za izvrsavanje programa rekonstruirajuci informacije sadrzane u
	 * instancama klase {@link Node} dobivene parsiranjem dokumenta pomocu
	 * {@link SmartScriptParser}
	 */
	private INodeVisitor visitor = new INodeVisitor() {

		@Override
		public void visitTextNode(TextNode node) {
			try {
				requestContext.write(node.getText());
			} catch (IOException e) {
				System.err.println("Cannot write on RequestContexts output stream");
				e.printStackTrace();
			}
		}

		@Override
		public void visitForLoopNode(ForLoopNode node) {
			String variable = node.getVariable().getName();

			String initialValue = node.getStartExpression().asText();
			String endValue = node.getEndExpression().asText();
			String stepString = node.getStepExpression().asText();
			String step = stepString != null ? stepString : "1"; 

			multistack.push(variable, new ValueWrapper(initialValue));

			while (multistack.peek(variable).numCompare(endValue) <= 0) {
				for (int i = 0; i < node.numberOfChildren(); i++) {
					node.getChild(i).accept(this);
				}
				multistack.peek(variable).increment(step);
			}
			multistack.pop(variable);			
		}

		@Override
		public void visitEchoNode(EchoNode node) {
			
			ITokenVisitor visitor =  new EvaluationTokenVisitor();
			for(Token token : node.getToken()) {
				token.accept(visitor);
			}
			
			Stack<Object> stack = ((EvaluationTokenVisitor) visitor).getStack();
			ArrayList<Object> reverse = new ArrayList<Object>(stack);
			for(int i = 0, len = reverse.size(); i < len; ++i) {
				try {
					requestContext.write(reverse.get(i).toString());
				} catch (IOException ignorable) {}
			}
		}

		@Override
		public void visitDocumentNode(DocumentNode node) {
			for(int i = 0; i < node.numberOfChildren(); ++i) {
				node.getChild(i).accept(this);
			}
		}
	};
	
	/**
	 * Razred implementira sucelje {@link ITokenVisitor}.
	 * Razred implementira ponasanje izvrsavanja programa kod nailaska na 
	 * instance razreda izvedenih iz {@link Token}.
	 * 
	 * @author jelena
	 *
	 */
	private class EvaluationTokenVisitor implements ITokenVisitor {
		
		/**
		 * Stack koristen u implementaciji metoda
		 */
		Stack<Object> stack = new Stack<Object>();

		@Override
		public void visitConstantDouble(TokenConstantDouble tokenConstantDouble) {
			stack.push(tokenConstantDouble.getValue());
		}

		@Override
		public void visitConstantInteger(
				TokenConstantInteger tokenConstantInteger) {
			stack.push(tokenConstantInteger.getValue());
		}

		/**
		 * Does nothing
		 */
		@Override
		public void visitEquals(TokenEquals tokenEquals) {
		}

		@Override
		public void visitFunction(TokenFunction tokenFunction) {
			Functions functions = new Functions();
			IFunction function = functions.getForName(tokenFunction);
			if(function == null) return;
			function.execute(stack, requestContext);
		}

		@Override
		public void visitOperator(TokenOperator tokenOperator) {
			String operator = tokenOperator.getSymbol().trim();
			ValueWrapper fOperand = null;
			fOperand = new ValueWrapper(stack.pop());
			Object sOperand = new ValueWrapper(stack.pop()).getValue();
			switch (operator) {
			case "+":
				fOperand.increment(sOperand);
				break;
			case "-":
				fOperand.decrement(sOperand);
				break;
			case "/":
				fOperand.divide(sOperand);
				break;
			case "*":
				fOperand.multiply(sOperand);
				break;
			default:
				break;
			}
			
			stack.push(fOperand.getValue());
		}

		/**
		 * Does nothing.
		 */
		@Override
		public void visitSpecial(TokenSpecial tokenSpecial) {
		}

		@Override
		public void visitString(TokenString tokenString) {
			stack.push(tokenString.asText());
		}

		/**
		 * Does nothing
		 */
		@Override
		public void visitText(TokenText tokenText) {
			stack.push(tokenText.asText());
		}

		@Override
		public void visitVariable(TokenVariable tokenVariable) {
			ValueWrapper a = multistack.peek(tokenVariable.getName());
			stack.push(a.getValue());
		}
		
		/**
		 * Metoda vraca stack pohranjen u instanci ove klase.
		 * @return stack pohranjen u instanci this
		 */
		public Stack<Object> getStack() {
			return stack;
		}
		
	}
	
	/**
	 * Konstruktor razreda {@link SmartScriptEngine}
	 * @param documentNode stablo dobiveno parsiranjem dokumenta koji sadrzi 
	 * program za iszvrsavanje.
	 * @param requestContext instanca {@link RequestContext} koju koristimo
	 */
	public SmartScriptEngine(DocumentNode documentNode, 
			RequestContext requestContext) {
		this.documentNode = documentNode;
		this.requestContext = requestContext;
	}
	
	/**
	 * Metoda sluzi za izvrsavanje programa.
	 */
	public void execute() {
		documentNode.accept(visitor);
	}
}
