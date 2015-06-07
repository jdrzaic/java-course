package hr.fer.zemris.java.custom.scripting.parser;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * Metoda sluzi za testiranje rada implementiranog parsera
 * Dokument se zadaje njegovim pathom kao argument naredbenog retka
 * @author jelena Drzaic
 */
public class SmartScriptTester {
	
	/**
	 * Metoda rekonstruira pocetni dokument 
	 * @param parsed DocumentNode korijen parsiranog dokumenta
	 * @return String rekonstruirani dokument
	 */
	public static String createOriginalDocumentBody(DocumentNode parsed) {
		return parsed.toString();
	}
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa
	 * Argumenti su objasnjeni u nastavku
	 * @param args argumenti naredbenog retka
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("One command line argument required!");
			System.exit(1);
		}
		SmartScriptParser parser = null;
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
		try {
			parser = new SmartScriptParser(docBody);
		}
		catch(SmartScriptParserException e) {
			System.out.println("Unable to parse document!");
			System.exit(-1);
		}
		catch(Exception e) {
			System.out.println("If this line ever executes, "
					+ "you have failed this class");
			System.exit(-1);
		}
		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = createOriginalDocumentBody(document);
		System.out.println(originalDocumentBody);
	}
}
