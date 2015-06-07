package hr.fer.zemris.java.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Razred reprezentira objekte koji sluze za pruzanje usluga
 * klijentima {@link SmartHttpServer}.
 * Kod pisanja na output stream instance ovog razreda,
 * najprije se ispisuje specijalno formatiran header. 
 * @author jelena
 *
 */
public class RequestContext {

	/**
	 * Ispis koji oznacava nemogusnost promjene vrijednosti varijabli
	 * zbog vec generiranog headera
	 */
	private static final String HEADER_GENERATED_INFO = "Header is already generated, "
			+ "cannot change value";
	
	/**
	 * Znak za novi red
	 */
	private static final String NEW_LINE = "\r\n";
	
	/**
	 * Razred reprezentira Cookie, koristene u implementaciji funkcionalnosti
	 * razreda {@link SmartHttpServer}.
	 * @author jelena
	 *
	 */
	public static class RCCookie {
		
		/**
		 * Ime cookie-a
		 */
		private String name;
		
		/**
		 * vrijednost cookie-a
		 */
		private String value;
		
		/**
		 * Domena cookie-a
		 */
		private String domain;
		
		/**
		 * Path cookie-a
		 * tj path do servera
		 */
		private String path;
		
		/**
		 * Najdulji zivot cookie-a
		 */
		private Integer maxAge;
		
		/**
		 * Tip cookiea
		 */
		private String type;
		
		/**
		 * Konstruktor razreda
		 * @param name ime cookiea
		 * @param value vrijednost cookiea
		 * @param maxAge najdulji vijek zivota cookiea
		 * @param domain domena servera ovog cookiea
		 * @param path put 
		 */
		public RCCookie(String name, String value, Integer maxAge, String domain, 
				String path) {
			super();
			this.name = name;
			this.value = value;
			this.domain = domain;
			this.path = path;
			this.maxAge = maxAge;
			this.type = null;
		}

		/**
		 * Metoda vraca vrijednost pohranjenu u varijabli name.
		 * @return name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Metoda vraca vrijednost pohranjenu u varijabli value.
		 * @return value
		 */
		public String getValue() {
			return value;
		}
		
		/**
		 * Metoda vraca vrijednost pohranjenu u varijabli domain.
		 * @return domain
		 */
		public String getDomain() {
			return domain;
		}
		
		/**
		 * Metoda vraca vrijednost pohranjenu u varijabli path.
		 * @return path
		 */
		public String getPath() {
			return path;
		}
		
		/**
		 * Metoda vraca vrijednost pohranjenu u varijabli maxAge.
		 * @return maxAge
		 */
		public Integer getMaxAge() {
			return maxAge;
		}
		
		/**
		 * Metoda postavlja tip cookiea na zadanu vrijednost
		 * @param type tip cookiea, npr."HttpOnly"
		 */
		public void setType(String type) {
			this.type = type;
		}
		
		@Override
		public String toString() { 
			StringBuilder sb = new StringBuilder();
			sb.append("Set-Cookie: " + name + "=\"" + value + "\"");  
			if(domain != null) {
				sb.append("; Domain=" + domain);
			}
			if(path != null) {
				sb.append("; Path=" + path);
			}
			if(maxAge != null) {
				sb.append("; Max-Age=" + maxAge);
			}
			if(type != null) {
				sb.append("; " + type);
			}
			return sb.toString();
		}
	}
	
	/**
	 * Stream za ispis podataka
	 */
	private OutputStream outputStream;
	
	/**
	 * Kodna stranica koju koristimo
	 */
	private Charset charset;
	
	/**
	 * String koji sadrzi informacije o kodnoj stranici
	 * koja se koristi
	 */
	private String encoding;
	
	/**
	 * kod koji se proslijeduje korisniku
	 */
	private int statusCode;
	
	/**
	 * text koji se proslijeduje korisniku
	 */
	private String statusText;
	
	/**
	 * Tip podataka koji se posluzuju
	 */
	private String mimeType;
	
	/**
	 * Parametri instance this
	 */
	private Map<String, String> parameters = new HashMap<String, String>();
	
	/**
	 * Trenutni parametri instance this
	 */
	private Map<String, String> temporaryParameters = new HashMap<String, String>();
	
	/**
	 * Trajni parametri instance this
	 */
	private Map<String, String> persistentParameters = new HashMap<String, String>();
	
	/**
	 * Cookiei instance this
	 */
	private List<RCCookie> outputCookies;
	
	/**
	 * true ako je generirano zaglavlje, false inace
	 */
	private boolean headerGenerated;
	
	/**
	 * Konsturktor razreda {@link RequestContext}.
	 * clanske varijable postavlja na proslijedene, osim ako je neka od
	 * mapa null, tada se stvara nova, prazna mapa.
	 * Defaultna kodna stanica je UTF-8,
	 * dok je defaultni tip podataka txt/html, a status 200 OK.
	 * @param outputStream stream na koji ispisujemo
	 * @param parameters parametri
	 * @param persistentParameters trajni parametri
 	 * @param outputCookies cookiei zahtjeva
	 */
	public RequestContext(OutputStream outputStream,
			Map<String, String> parameters,
			Map<String, String> persistentParameters,
			List<RCCookie> outputCookies) {
		super();
		if(outputStream == null) {
			throw new IllegalArgumentException("Output stream must not be null");
		}else {
			this.outputStream = outputStream;
		}
		this.persistentParameters = persistentParameters == null ? new HashMap<String,String>() : persistentParameters;
		this.parameters = parameters == null ? new HashMap<String,String>() : parameters;
		this.outputCookies = outputCookies == null ? new ArrayList<RequestContext.RCCookie>() : outputCookies;
		this.encoding = "UTF-8";
		this.statusCode = 200;
		this.statusText = "OK";
		this.mimeType = "text/html";
		this.headerGenerated = false;
	}
	
	/**
	 * Metoda sluzi za dodavanje cookiea
	 * @param cookie cookie koji dodajemo u internu listu cookiea
	 */
	public void addRCCookie(RCCookie cookie) {
		outputCookies.add(cookie);
	}
	
	/**
	 * Metoda postavlja kodnu stranicu na proslijedenu vrijednost
	 * @param encoding kodna stanica
	 */
	public void setEncoding(String encoding) {
		this.encoding = setStringValue(this.encoding, encoding);
	}
	
	/**
	 * Metoda postavlja status kod instance this.
	 * Kodovi su standardni.
	 * Ukoliko nije moguce provesti operaciju(header vec generiran),
	 * izbacuje se {@link RuntimeException}.
	 * @param statusCode kod na koji postavljamo
	 */
	public void setStatusCode(int statusCode) {
		if(!headerGenerated) {
			this.statusCode = statusCode;
		}else {
			throw new RuntimeException(HEADER_GENERATED_INFO);
		}
	}
	
	/**
	 * Metoda sluzi za postavljanje statusnog teksta.
	 * Ukoliko je header vec generiran, izbacujemo 
	 * {@link RuntimeException}.
	 * @param statusText statusni tekst koji isporucujemo
	 */
	public void setStatusText(String statusText) {
		this.statusText = setStringValue(this.statusText, statusText);
	}
	
	/**
	 * Metoda sluzi za postavljanje mymeType-a.
	 * @param mimeType string na koji postavljamo mimeType
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = setStringValue(this.mimeType, mimeType);
	}

	/**
	 * Metoda sluzi za dohvat objekta pohranjenog u mapi parametara,
	 * mapiranog pod kljucem name
	 * @param name kljuc pod kojim dohvacamo parametar
	 * @return vrijednost na navedenom mjestu, ako postoji, 
	 * null inace
	 */
	public String getParameter(String name) {
		return parameters.get(name);
	}
	
	/**
	 * Metoda vraca sve kljuceve pod kojima su pohranjeni parametri u
	 * mapi parametara instance this
	 * @return kljucevi u mapi parametara
	 */
	public Set<String> getParameterNames() {
		return new HashSet<String>(parameters.keySet());
	}
	
	/**
	 * Metoda sluzi za dohvat objekta pohranjenog u mapi trajnih parametara,
	 * mapiranog pod kljucem name
	 * @param name kljuc pod kojim dohvacamo parametar
	 * @return vrijednost na navedenom mjestu, ako postoji, 
	 * null inace
	 */
	public String getPersistentParameter(String name) {
		return persistentParameters.get(name);
	}
	
	/**
	 * Metoda vraca sve kljuceve pod kojima su pohranjeni parametri u
	 * mapi trajnih parametara instance this.
	 * @return kljucevi u mapi trajnih parametara
	 */
	public Set<String> getPersistentParameterNames(){
		return new HashSet<String>(persistentParameters.keySet());
	}
	
	/**
	 * Metoda postavlja parametar s kljucem name i vrijednosti value
	 * u mapu trajnih parametara.
	 * @param name kljuc pod kojim spremamo
	 * @param value vrijednost koji spremamo
	 */
	public void setPersistentParameter(String name, String value) {
		persistentParameters.put(name, value);
	}
	
	/**
	 * Metoda sluzi za brisanje parova iz mape trajnih parametara,
	 * brise se vrijednost pohranjena pod kljucem name.
	 * @param name kljuc pod kojim brisemo vrijednost
	 */
	public void removePersistentParameter(String name) {
		persistentParameters.remove(name);
	}
	
	/**
	 * Metoda sluzi za dohvat objekta pohranjenog u mapi trenutnih parametara,
	 * mapiranog pod kljucem name
	 * @param name kljuc pod kojim dohvacamo parametar
	 * @return vrijednost na navedenom mjestu, ako postoji, 
	 * null inace
	 */
	public String getTemporaryParameter(String name) {
		return temporaryParameters.get(name);
	}
	
	/**
	 * Metoda vraca sve kljuceve pod kojima su pohranjeni parametri u
	 * mapi trajnih parametara instance this.
	 * @return kljucevi u mapi trajnih parametara
	 */
	public Set<String> getTemporaryParameterNames() {
		return new HashSet<String>(temporaryParameters.keySet());
	}
	
	/**
	 * Metoda postavlja parametar s kljucem name i vrijednosti value
	 * u mapu trenutnih parametara.
	 * @param name kljuc pod kojim spremamo
	 * @param value vrijednost koji spremamo
	 */
	public void setTemporaryParameter(String name, String value) {
		temporaryParameters.put(name, value);
	}
	
	/**
	 * Metoda sluzi za brisanje parova iz mape trenutnih parametara,
	 * brise se vrijednost pohranjena pod kljucem name.
	 * @param name kljuc pod kojim brisemo vrijednost
	 */
	public void removeTemporaryParameter(String name) {
		temporaryParameters.remove(name);
	}
	
	/**
	 * Metoda ispisuje dani argument na output stream-clansku varijablu razreda 
	 * this.Pri prvom pozivu najprije se generira header.
	 * @param data podaci koje ispisujemo
	 * @return this
	 * @throws IOException kod problema s ispisom
	 */
	public RequestContext write(byte[] data) throws IOException {
		if(!headerGenerated) {
			headerGenerated = true;
			charset = Charset.forName(encoding);
			String header = createHeader();
			outputStream.write(header.getBytes(StandardCharsets.ISO_8859_1));
		}
		outputStream.write(data);
		return this;
	}
	
	/**
	 * Metoda generira header u obliku prikladnom za isporuku nekom
	 * browseru.
	 * @return generirani header, tipa {@link String}
	 */
	public String createHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("HTTP/1.1 " + statusCode + " " + statusText + NEW_LINE);
		sb.append("Content-Type:" + mimeType);
		if(mimeType.startsWith("text/")) {
			sb.append("; charset=" + encoding);
		}
		sb.append(NEW_LINE);
		addCookiesInfo(sb);
		sb.append(NEW_LINE);
		return sb.toString();
	}
	
	/**
	 * Metoda obavlja isti posao kao metoda write odozgo,
	 * jedino sto prije svega pretvara predani argument u 
	 * polje bajtova
	 * @param text String koji ispisujemo
	 * @return this
	 * @throws IOException kod problema s ispisom
	 */
	public RequestContext write(String text) throws IOException{
		if(!headerGenerated) {
			charset = Charset.forName(encoding);
		}
		byte[] data = text.getBytes(charset);
		return write(data);
	}

	/**
	 * Metoda u ispis dodaje podatke o pridruzenim cookie-ma instanci
	 * this.
	 * @param sb StringBuilder u kojeg spremamo podatke za ispis
	 */
	private void addCookiesInfo(StringBuilder sb) {
		if(outputCookies.isEmpty()) return;
		for(RCCookie cookie : outputCookies) {
			sb.append(cookie.toString()).append(NEW_LINE);
		}
	}

	/**
	 * Metoda vraca drugi argument s kojim je pozvana, ako header jos nije
	 * izgeneriran za ovu instancu, inace bava {@link RuntimeException}.
	 * @param toInit string za inicijalizaciju
	 * @param newParam string kojim inicijaliziramo
	 * @return string newParam ako header nije generiran
	 */
	private String setStringValue(String toInit, String newParam) {
		if(!headerGenerated) {
			return newParam;
		}else {
			throw new RuntimeException(HEADER_GENERATED_INFO);
		}
	}
}
