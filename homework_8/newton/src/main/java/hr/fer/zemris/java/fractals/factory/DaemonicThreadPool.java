package hr.fer.zemris.java.fractals.factory;

import java.util.concurrent.ThreadFactory;

/**
 * Razred implementira sucelje {@link ThreadFactory}.
 * Sluzi za stvaranje demonskih dretvi.
 * @author jelena Drzaic
 *
 */
public class DaemonicThreadPool implements ThreadFactory{

	/**
	 * Metoda vraca novu dretvu s demonskom zastavicom postavljenom
	 * na true.
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
}
