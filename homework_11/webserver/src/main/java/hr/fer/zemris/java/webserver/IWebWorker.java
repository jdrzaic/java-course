package hr.fer.zemris.java.webserver;

/**
 * Sucelje reprezentira objekte sposobne za obradu
 * zahtjeva klijenta servera {@link SmartHttpServer}.
 * Nudi metodu processRequest() koja odraduje zadani posao.
 * @author jelena
 *
 */
public interface IWebWorker {
	
	/**
	 * Metoda odraduje zadani posao.
	 * Eventualni rezultat ispisuje na input stream dobiven od
	 * instance {@link RequestContext}.
	 * @param context instanca {@link RequestContext},
	 * Potrazitelj usluge.
	 */
	public void processRequest(RequestContext context);
}
