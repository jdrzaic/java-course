package hr.fer.zemris.java.webserver;

import hr.fer.zemris.java.custom.scripting.exec.SmartScriptEngine;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.webserver.RequestContext.RCCookie;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Razred implementira http server.
 * Server je u mogucnosti procesuirati zahtjeve za binarnim file-ovima,
 * izvrsavati skripte prilagodene za {@link SmartScriptParser}, 
 * ispisivati proslijedene parametre(echo), iscrtavati krug,
 * ispisivati broj znakova proslijedenog parametra i sl.
 * server koristi mehanizam cookiea.
 * Server prestaje s radom kada se u konzoli unese naredba 'stop'.
 * @author jelena
 *
 */
public class SmartHttpServer {
	
	/**
	 * razlika izmedu provjera zivucih sesija
	 */
	static final long BETWEEN_CONTROL = 5000 * 60;
	
	/**
	 * Abeceda, za generiranje slucajnih stringova
	 */
	static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * Duljina random generiranog stringa, koristenog za sid cookie
	 */
	private static final int RANDOM_LEN = 20;
	
	/**
	 * IP Adresa servera
	 */
	private String address;
	
	/**
	 * Port na kojem server slusa
	 */
	private int port;
	
	/**
	 * Broj dretvi workera
	 */
	private int workerThreads;
	
	/**
	 * Najdulje trajanje sesije
	 */
	private int sessionTimeout;
	
	/**
	 * Mapa mimeType-ova, tj. tipova dokumenata
	 */
	private Map<String,String> mimeTypes = new HashMap<String, String>();
	
	/**
	 * Glavna dretva, odraduje
	 * prihvat novih klijenata
	 */
	private ServerThread serverThread;
	
	/**
	 * Instanca {@link ExecutorService}-a
	 */
	private ExecutorService threadPool;
	
	/**
	 * Put do dostupnih dokumenata
	 */
	private Path documentRoot;
	
	/**
	 * Mapa dostupnih workera kojima se moze pristupiti sa
	 * address:port/name_of_worker
	 */
	private Map<String,IWebWorker> workersMap = new HashMap<String, IWebWorker>();
	
	/**
	 * Mapa aktivnih sesija
	 */
	private Map<String, SessionMapEntry> sessions =
			new HashMap<String, SmartHttpServer.SessionMapEntry>();
	
	/**
	 * Generator random brojeva
	 */
	private Random sessionRandom = new Random();
	
	/**
	 * Konstruktor razreda.
	 * Postavlja odgovarajuve vrijednosti na vrijednosti zapisane u 
	 * konfiguracijskim file-ovima.
	 * @param configFileName put do .properties file-a za konfiguraciju servera
	 * 
	 */
	public SmartHttpServer(String configFileName) {
		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(configFileName);
			properties.load(input);
			address = properties.getProperty("server.address");
			port = Integer.parseInt(properties.getProperty("server.port"));
			workerThreads = Integer.parseInt(properties.getProperty(
					"server.workerThreads"));
			sessionTimeout = Integer.parseInt(
					properties.getProperty("session.timeout"));
			documentRoot = Paths.get(properties.getProperty("server.documentRoot"));
			
			//tipovi dokumenata
			String mimeConfig = properties.getProperty("server.mimeConfig");
			setMimeTypes(mimeConfig);
			
			//dostupni IWebWorker-i
			String workersConfig = properties.getProperty("server.workers");
			setWorkersMap(workersConfig);
		}catch(IOException e) {
			System.err.println("Unable to read from" + configFileName);
			return;
		}finally{
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metoda koja, kada je pozvana, pokrece server.
	 */
	protected synchronized void start() {
		if(serverThread == null) {
			serverThread = new ServerThread();
		}
		controlThread.setDaemon(true);
		controlThread.start();
		serverThread.start();
		threadPool = Executors.newFixedThreadPool(workerThreads);
		System.out.println("Server is ready...");
	}
	
	/**
	 * Metoda, kada je pozvana, zaustavlja rad servera
	 */
	protected synchronized void stop() {
		serverThread.stopRunning();
		threadPool.shutdown();
	}
	
	/**
	 * Razred implementira sucelje {@link Thread}.
	 * Razred implementira prihvat novih klijenata.
	 * @author jelena
	 *
	 */
	protected class ServerThread extends Thread {
		
		/**
		 * Je li prihvatljivo spajanje klijenata
		 */
		private boolean acceptable;
		
		@Override
		public void run(){
			ServerSocket serverSocket = null;
			
			try {
				serverSocket = new ServerSocket();
				serverSocket.bind(
					new InetSocketAddress((InetAddress)null, port)
				);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			acceptable = true;
			
			while (acceptable) {
				try {
					Socket client = serverSocket.accept();
					ClientWorker cw = new ClientWorker(client);
					threadPool.submit(cw);
				} catch (IOException ignorable) {}
			}
			try {
				serverSocket.close();
			} catch (IOException ignorable) {}
		}
		
		/**
		 * Metoda zaustavlja prihvat novih klijenata na server.
		 */
		public void stopRunning() {
			acceptable = false;
		}
	}
	
	/**
	 * Razred implementira sucelje {@link Runnable}.
	 * Implementira posao koji server odraduje vezano uz pojedinog 
	 * aktivnog klijenta.
	 * @author jelena
	 *
	 */
	private class ClientWorker implements Runnable {
	
		/**
		 * Klijentski socket
		 */
		private Socket csocket;
		
		/**
		 * Klijentski input stream
		 */
		private PushbackInputStream istream;
		
		/**
		 * Klijentski output stream
		 */
		private OutputStream ostream;
		
		/**
		 * Verzija HTTP protokola iz zahtjeva klijenta
		 */
		@SuppressWarnings("unused")
		private String version;
		
		/**
		 * Metoda kojom klijent potrazuje podatke.
		 * podrzana je metoda GET
		 */
		@SuppressWarnings("unused")
		private String method;
		
		/**
		 * Parametri iz klijentovog zahtjeva
		 */
		private Map<String,String> params = new HashMap<String, String>();
		
		/**
		 * Parametri koristeni za realizaciju mehanizma cookiea
		 */
		private Map<String,String> permPrams;
		
		/**
		 * Cookiei klijenta this
		 */
		private List<RCCookie> outputCookies = new ArrayList<RequestContext.RCCookie>();
		
		/**
		 * Identifikacija klijenta
		 */
		private String SID;
		
		/**
		 * Path do trazenog dokumenta
		 */
		private String requestedPath;
		
		/**
		 * Konstruktor razreda.
		 * @param csocket klijentski socket
		 */
		public ClientWorker(Socket csocket) {
			super();
			this.csocket = csocket;
		}
		
		@Override
		public void run() {
			try {
				istream = new PushbackInputStream(csocket.getInputStream());
				ostream = csocket.getOutputStream();
				List<String> request = readRequest();
				if(request.size() < 1) {
					reportError(400, "Bad request");
					return;
				}
				
				//ispis zahtjeva
				System.err.println("New Request:");
				for (String line : request) {
					System.out.println(line);
				}
				
				//prvi redak zahtjeva
				String firstLine = request.get(0);
				if(!extractFirstLine(firstLine)) {
					reportError(400, "Bad request");
					return;
				}
				 
				String path = null;
				String paramString = null;
				//ako imamo neke parametre
				if(requestedPath.contains("?")) {
					String[] components = requestedPath.split("\\?");
					if(components.length != 2){
						reportError(400, "Bad Request");
					}
					//path do filea
					path = components[0].trim();
					//parametri
					paramString = components[1].trim();
				}else {
					path = requestedPath.trim();
				}
	
				//provjeri postoji li vec klijent
				String sidCandidate = checkSession(request);
				//ne postoji
				if (sidCandidate == null) {
					generateSession();
					System.err.println("New client, SID= " + SID);
				} else {
					SessionMapEntry entry = sessions.get(sidCandidate);
					//postoji klijent,ali nemamo podatke o njegovoj sesiji
					if (entry == null) { 
						generateSession(); 
					//timeout
					} else if (entry.validUntil < System.currentTimeMillis()) {
						System.out.println("Time is up" + entry.validUntil + " "  + System.currentTimeMillis());
						generateSession();
					} else {
						System.out.println("sid" + entry.sid);
						SID = entry.sid;
						entry.validUntil = System.currentTimeMillis() + sessionTimeout * 1000;
						permPrams = entry.map;
					}
				}

				parseParameters(paramString);
				
				//workers
				if(path.startsWith("/ext")) {
					mapExtWorker(path);
				}
				
				//workers iz mape
				if(workersMap.containsKey(path)) {
					workersMap.get(path).processRequest(
							new RequestContext(ostream,
									params, permPrams, outputCookies));
					return;
				}
				
				Path requestedFile = Paths.get(documentRoot + path);
				String extension = getExtension(requestedFile);
				String mimeType = getMimeTypeFor(extension);
				RequestContext rc = new RequestContext(ostream, params, permPrams, outputCookies);
				rc.setMimeType(mimeType);
				rc.setStatusCode(200);
				rc.setStatusText("OK");
				
				//skripta-izvrsi program
				if(extension.equals("smscr")) {
					serveSmartScript(requestedFile, rc);
				}else {
					//neki file
					serveFile(requestedFile, rc);
				}
 			} catch (Exception ignorable) {}
		}

		/**
		 * Metoda provjerava postoji li u klijentovom zahtjevu 
		 * informacija o cookie-u "sid".
		 * Ako postoji, vraca njegovu vrijednost,
		 * inace, null.
		 * @param request klijentov zahtjev
		 * @return sid ako postoji, null inace
		 */
		private synchronized String checkSession(List<String> request) {
			
			try {
				Pattern pattern = Pattern.compile("^.*sid=\"(.*)\".*");
				for (String line : request) {
					if (line.startsWith("Cookie: ")) { 
						Matcher matcher = pattern.matcher(line);
						if (matcher.find()) {
							String sidCandidate = matcher.group(1);
							return sidCandidate;
						}
					}
				}
			} catch (Exception ignorable) {}
			return null;
		}

		/**
		 * Metoda stvara novi {@link SessionMapEntry}, tj stvara novu korisnicku
		 * sessiju, dodaje cookie "sid", postavlja timeout.
		 */
		public synchronized void generateSession() {
			SID = generateString();
			permPrams = new HashMap<>();
			SessionMapEntry entry = new SessionMapEntry();
			entry.sid = SID;
			entry.validUntil = System.currentTimeMillis() + sessionTimeout * 1000;
			entry.map = permPrams;
			sessions.put(SID, entry);
			RCCookie cookie = new RequestContext.RCCookie("sid", SID, null, address	, "/");
			cookie.setType("HttpOnly");
			outputCookies.add(cookie);
		}
		
		/**
		 * Metoda u mapu workera sprema dobiveni worker, ako je za takvog
		 * moguce provesti navedeno.
		 * @param path klijentov dan put do implementacije {@link IWebWorker}-a
		 * @throws Exception u slucaju nemogucnosti loadanja {@link IWebWorker}-a
		 */
		private void mapExtWorker(String path) throws Exception{
			//preskocimo /ext/
			String className = path.trim().substring(5, path.trim().length());
			String fqcn = "hr.fer.zemris.java.webserver.workers." + className;
			Class<?> referenceToClass = this.getClass().getClassLoader().loadClass(fqcn);
			Object newObject = referenceToClass.newInstance();
			IWebWorker iww = (IWebWorker)newObject;
			workersMap.put(path, iww);
		}

		/**
		 * Metoda procesuira korisnikov zahtjev za izvodenjem programa iz
		 * dane skripte.
		 * @param requestedFile skripta koju izvrsavamo
		 * @param rc klijentska instanca {@link RequestContext}, klijentov
		 * zahtjev
		 */
		private  void serveSmartScript(Path requestedFile, RequestContext rc) {
			String documentBody = null;
			try {
				documentBody = new String(Files.readAllBytes(requestedFile),
						StandardCharsets.UTF_8);
				new SmartScriptEngine(
						new SmartScriptParser(documentBody).getDocumentNode(),
						new RequestContext(ostream, params, permPrams, outputCookies)
						).execute();
				csocket.close();
			} catch (IOException e) {}
		}

		/**
		 * Metoda koja procesuira klijentov zahtjev za opcenitim, 
		 * nespecijalnim file-om. Klijent kao rezultat dobiva zapis tog file-a
		 * u zeljenom obliku.
		 * @param requestedFile zatrazeni file
		 * @param rc reprezentacija klijentovog zahtjeva, {@link RequestContext}
		 */
		private void serveFile(Path requestedFile, RequestContext rc) {
			BufferedInputStream stream = null;
			try {
				stream = new BufferedInputStream(new FileInputStream(
						requestedFile.toFile()));
				byte[] buffer = new byte[1024];
				int read = 0;
				while ((read = stream.read(buffer)) != -1) {
					byte[] readPart = Arrays.copyOfRange(buffer, 0, read);
					rc.write(readPart);
				}
				csocket.close();
			} catch (IOException e) {
			} finally {
				if (stream != null) {
					try { 
						stream.close(); 
					} catch (IOException ignorable) {}
				}
			}
		}

		/**
		 * Metoda vraca tip dokumenta, kojeg ekstrahira iz ekstenzije zatrazenog 
		 * dokumenta.Ako tip nije poznat serveru, default je "application/octet-stream".
		 * @param extension ekstenzija trazenog dokumenta
		 * @return tip dokumenta
		 */
		private String getMimeTypeFor(String extension) {
			if(mimeTypes.containsKey(extension)) {
				return mimeTypes.get(extension);
			}
			return "application/octet-stream";
		}

		/**
		 * Metoda iz puta do file-a ekstrahira ekstenziju.
		 * @param file put do zeljenog filea
		 * @return ekstenzija filea
		 */
		private String getExtension(Path file) {
			String extension = null;
			if(!file.startsWith(documentRoot)) {
				reportError(403, "Forbidden");
			}else if(!file.toFile().exists()) {
				reportError(404, "Not Found");
			}else if(!file.toFile().canRead()) {
				reportError(404, "Not Found");
			}else {
				int position = file.getFileName().toString().lastIndexOf(".");
				extension = file.getFileName().toString().substring(
						position + 1, file.getFileName().toString().length());
			}
			return extension;
		}

		/**
		 * Metoda iz zahtjeva parse-a parametre, te put do file-a.
		 * @param paramString string koji sadrzi path do filea i parametre,
		 * u standardnom obliku HTTP zahtjeva
		 */
		private void parseParameters(String paramString) {
			if(paramString == null) {
				return;
			}
			String[] params = paramString.split("&");
			for(String param : params) {
				String[] pair = param.split("=");
				this.params.put(pair[0], pair[1]);
			}
		}

		/**
		 * Metoda iz prve linije zahtjeva parsira metodu, verziju,
		 * te put+parametre.
		 * U slucaju nelegalnosti metode(nije GET) te verzije(nije HTTP/1.1 niti
		 * HTTP/1.0) vraca false, inace true.
		 * 
		 * @param firstLine prva linija zahtjeva
		 * @return true ako je linija legalan zahtjev, false inace
		 */
		private boolean extractFirstLine(String firstLine) {
			firstLine = firstLine.trim();
			String[] components = firstLine.split("\\s+");
			if(components.length != 3) {
				return false;
			}
			if(!components[0].trim().equals("GET") || (
					!components[2].trim().equals("HTTP/1.0") && 
					!components[2].trim().equals("HTTP/1.1"))) {
				return false;
			}
			method = components[0];
			version = components[2];
			requestedPath = components[1];
			return true;
		}

		/**
		 * Metoda klijentu proslijednuje potruku o gresci prilikom obrade zahtjeva.
		 * @param code kod greske
		 * @param text tekst greske
		 */
		private void reportError(int code, String text) {
			RequestContext c = new RequestContext(ostream, null, null, null);
			c.setStatusText(text);
			c.setStatusCode(code);
			try {
				c.write(text);
				c.write(Integer.toString(code));
				while(true) {
					try {
						ostream.flush();
						csocket.close();
						break;
					}catch(Exception ignorable) {}
				}
			} catch (IOException ignorable) {}
		}

		/**
		 * Metoda parsira korisnikov request u listu linija 
		 * zahtjeva.
		 * @return lista linija klijentovog zahtjeva
		 */
		private List<String> readRequest() {
			List<String> lines = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(
						new InputStreamReader(
							istream, StandardCharsets.UTF_8));
			String line;
			try {
				while((line = reader.readLine()) != null) {
					if(line.trim().isEmpty()) {
						break;
					}
					lines.add(line);
				}
			} catch (IOException e) {}
			
			return lines;
		}
	}
	
	/**
	 * Metoda cita iz workers konfiguracijskog filea informacije o 
	 * dostupnim implementacijama {@link IWebWorker}-a.
	 * Ako takvi postoje, dodaje ih u mapu workera.
	 * @param workersConfig konfiguracijski file
	 */
	private synchronized void setWorkersMap(String workersConfig) {
		//ako ima vise istih-dodati!

		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(workersConfig);
			properties.load(input);
			Set<Object> keys = properties.keySet();
			for(Object key : keys) {
				String path = (String)key;
				String fqcn = properties.getProperty((String)key);
				Class<?> referenceToClass = this.getClass().getClassLoader().loadClass(fqcn);
				Object newObject = referenceToClass.newInstance();
				IWebWorker iww = (IWebWorker)newObject;
				workersMap.put(path, iww);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Unable to read from" + workersConfig);
			return;
		}finally{
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metoda generira slucajni string duljine 20 znakova.
	 * @return generirani string
	 */
	private String generateString() {
		StringBuilder sb = new StringBuilder(RANDOM_LEN);
		for(int i = 0; i < RANDOM_LEN; ++i) { 
			sb.append(ABC.charAt(sessionRandom.nextInt(ABC.length()) ) );
		}
		return sb.toString();
	}

	/**
	 * Metoda cita informacije iz konfiguracijskog filea o 
	 * dostupnim tipovima dokumenata. Ako takvi postoje, pohranjuje ih.
	 * @param mimeConfig konfiguracijski file
	 */
	private void setMimeTypes(String mimeConfig) {
		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(mimeConfig);
			properties.load(input);
			Set<Object> keys = properties.keySet();
			for(Object key : keys) {
				mimeTypes.put((String)key, properties.getProperty((String)key));
			}
		}catch(IOException e) {
			System.err.println("Unable to read from" + mimeConfig);
			return;
		}finally{
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Razred predstavlja klijentsku sesiju,
	 * Sadrzi informacije o cookieu sid, te o vremenskoj 
	 * validnosti sesije.
	 * @author jelena
	 *
	 */
	private static class SessionMapEntry {
		
		/**
		 * Identifikator sesije
		 */
		String sid = null;
		
		/**
		 * Najdulji zivotni vijek
		 */
		long validUntil;
		
		/**
		 * Klijentski podatci
		 */
		Map<String,String> map = new ConcurrentHashMap<String, String>();
		
	}
	
	/**
	 * Instanca razreda naslijedenog iz {@link Thread}.
	 * Implementira sljedece ponasanje;
	 * Svakih BETWEEN_CONTROL milisekundi prolazi po mapi
	 * sesija te izbacuje one kojima je istekao rok validnosti.
	 */
	private Thread controlThread = new Thread() {
	
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(BETWEEN_CONTROL);
				} catch (InterruptedException ignorable) {}
				Set<String> sids = sessions.keySet();
				for(String sid : sids) {
					SessionMapEntry entry = sessions.get(sid);
					if(entry.validUntil < System.currentTimeMillis()) {
						sessions.remove(sid);
					}
				}
			}	
		}
	};
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije, 
	 * zanemaruju se.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println(".config file expected");
			return;
		}
		try{
			SmartHttpServer server = new SmartHttpServer(args[0]);
			System.out.println("Starting server...");
			System.out.println("Server stops when entering 'stop'");
			server.start();
			BufferedReader br = 
	                new BufferedReader(new InputStreamReader(System.in));
			String toExit = br.readLine().trim();
			if(toExit.toLowerCase().equals("stop")) {
				server.stop();
				System.exit(0);
			}
		}catch(Exception e) {
			System.err.println("Error running server");
		}

	}
}

