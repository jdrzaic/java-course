package hr.fer.zemris.java.custom.scripting.demo;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.INodeVisitor;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Razred implementira ispis stabla parsiranog dokumenta
 * do kojeg je put dan kao jedini argument  komandne linije.
 * Parsiranje vrsi instanca {@link SmartScriptParser}.
 * Ispis se obavlja koristenjem razreda {@link WriterVisitor}.
 * @author jelena
 *
 */
public class TreeWriter {

	/**
	 * Metoda koja se pokrece prilikom pokretanja programa.
	 * @param args argumenti komandne linije, ocekuje put do file-a
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.err.println("One command line argument required!");
			System.exit(1);
		}
		
		String given = null;
		
		try {
			given = new String(Files.readAllBytes(Paths.get(args[0])),
				StandardCharsets.UTF_8);
		}
		catch(Exception e) {
			System.err.println("Unable to open this document!");
			System.exit(-1);
		}
		String docBody = given;
		SmartScriptParser p = new SmartScriptParser(docBody);
		
		WriterVisitor visitor  = new WriterVisitor();
		p.getDocumentNode().accept(visitor);
	}
	
	/**
	 * Razred implementira obilazak instanci razreda {@link Node},
	 * tj. onih izvedenih iz tog razreda.
	 * Razred implementira sucelje {@link INodeVisitor}.
	 * @author jelena
	 *
	 */
	public static class WriterVisitor implements INodeVisitor {

		@Override
		public void visitTextNode(TextNode node) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < node.getText().length(); ++i) {
				if (node.getText().charAt(i) == '\\' || node.getText().charAt(i) == '{') {
					sb.append("\\" + node.getText().charAt(i));
				} 
				else {
					sb.append(node.getText().charAt(i));
				}
			}
			System.out.print(sb.toString());
		}

		@Override
		public void visitForLoopNode(ForLoopNode node) {
			StringBuilder sb = new StringBuilder(
					"{$FOR " + node.getVariable().toString() + " " + 
					 node.getStartExpression().toString() + " " +
					 node.getEndExpression().toString());
			if (node.getStepExpression() != null) {
				sb.append(" " + node.getStepExpression().toString());
			}
			sb.append("$}");
			System.out.print(sb.toString());
			sb.setLength(0);
			for (int i = 0; i < node.numberOfChildren(); ++i) {
				node.getChild(i).accept(this);
			}
			System.out.print("{$END$}");			
		}

		@Override
		public void visitEchoNode(EchoNode node) {
			StringBuilder sb = new StringBuilder("{$=");
			for (int i = 0; i < node.getToken().length; ++i) {
				sb.append(node.getToken()[i].toString() + " ");
			}
			sb.append("$}");	
			System.out.print(sb.toString());
		}

		@Override
		public void visitDocumentNode(DocumentNode node) {
			for (int i = 0; i < node.numberOfChildren(); ++i) {
				node.getChild(i).accept(this);
			}
		}
	}
	
}
