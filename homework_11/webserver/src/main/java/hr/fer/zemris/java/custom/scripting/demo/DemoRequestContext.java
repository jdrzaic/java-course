package hr.fer.zemris.java.custom.scripting.demo;
import hr.fer.zemris.java.webserver.RequestContext;
import hr.fer.zemris.java.webserver.RequestContext.RCCookie;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Razred reprezentira koristenje funkcionalnosti razreda
 * {@link RequestContext}.
 * Vrse se provjere koristenjem razlicitih kodnih stranica.
 * @author jelena
 *
 */
public class DemoRequestContext {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije, zanemaruju se
	 * @throws IOException kod problema s inputom/outputom
	 */
	public static void main(String[] args) throws IOException {
		demo1("primjer1.txt", "ISO-8859-2");
		demo1("primjer2.txt", "UTF-8");
		demo2("primjer3.txt", "UTF-8");
	}
	
	/**
	 * Metoda testira koristenje razreda {@link RequestContext},
	 * te stvara file naziva filePath, generiranog kodnom stranicom encoding
	 * @param filePath naziv file-a koji se generira
	 * @param encoding kodna stranica
	 * @throws IOException u slucaju problema s inputom/outputom
	 */
	private static void demo1(String filePath, String encoding) throws IOException {
		OutputStream os = Files.newOutputStream(Paths.get(filePath));
		RequestContext rc = new RequestContext(os, new HashMap<String, String>(),
				new HashMap<String, String>(),
				new ArrayList<RequestContext.RCCookie>());
		rc.setEncoding(encoding);
		rc.setMimeType("text/plain");
		rc.setStatusCode(205);
		rc.setStatusText("Idemo dalje");
		// Only at this point will header be created and written...
		rc.write("Čevapčići i Šiščevapčići.");
		os.close();
	}	
	/**
	 * Metoda testira koristenje razreda {@link RequestContext},
	 * te stvara file naziva filePath, generiranog kodnom stranicom encoding
	 * @param filePath naziv file-a koji se generira
	 * @param encoding kodna stranica
	 * @throws IOException u slucaju problema s inputom/outputom
	 */
	private static void demo2(String filePath, String encoding) throws IOException {
		OutputStream os = Files.newOutputStream(Paths.get(filePath));
		RequestContext rc = new RequestContext(os, new HashMap<String, String>(),
				new HashMap<String, String>(),
				new ArrayList<RequestContext.RCCookie>());
		rc.setEncoding(encoding);
		rc.setMimeType("text/plain");
		rc.setStatusCode(205);
		rc.setStatusText("Idemo dalje");
		rc.addRCCookie(new RCCookie("korisnik", "perica", 3600, "127.0.0.1", "/"));
		rc.addRCCookie(new RCCookie("zgrada", "B4", null, null, "/"));
		// Only at this point will header be created and written...
		rc.write("Čevapčići i Šiščevapčići.");
		os.close();
	}
}
