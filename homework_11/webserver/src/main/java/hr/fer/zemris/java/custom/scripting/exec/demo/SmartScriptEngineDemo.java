package hr.fer.zemris.java.custom.scripting.exec.demo;

import hr.fer.zemris.java.custom.scripting.exec.SmartScriptEngine;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.webserver.RequestContext;
import hr.fer.zemris.java.webserver.RequestContext.RCCookie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Razred implementira koristenje razreda {@link SmartScriptEngine}.
 * Skripta koja se izvrsava proslijeduje se kao jedini argument komandne linije.
 * Ispisuje se vrijednost zapisana u mapi instance {@link RequestContext} nakon 
 * izvrsavanja programa.
 * @author jelena
 *
 */
public class SmartScriptEngineDemo {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije
	 * @throws IOException u slucaju problema s inputom/outputom
	 */
	public static void main(String[] args) throws IOException {
		String documentBody = new String(Files.readAllBytes(Paths.get(args[0])),
				StandardCharsets.UTF_8);		
		Map<String,String> parameters = new HashMap<String, String>();
		Map<String,String> persistentParameters = new HashMap<String, String>();
		List<RCCookie> cookies = new ArrayList<RequestContext.RCCookie>();
		
		persistentParameters.put("brojPoziva", "3");
		RequestContext rc = new RequestContext(System.out, parameters, persistentParameters, cookies);
		new SmartScriptEngine(
				new SmartScriptParser(documentBody).getDocumentNode(), rc
				).execute();
		System.out.println("Vrijednost u mapi: "+rc.getPersistentParameter("brojPoziva"));
	}
}
