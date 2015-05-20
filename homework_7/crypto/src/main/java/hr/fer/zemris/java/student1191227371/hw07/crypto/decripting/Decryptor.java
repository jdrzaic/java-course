package hr.fer.zemris.java.student1191227371.hw07.crypto.decripting;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Sucelje implementira dekripciju datoteka.
 * @author Jelena Drzaic
 *
 */
public interface Decryptor {

	/**
	 * Metoda sluzi za dekripciju danog file-a i zapis istog u drugi proslijedeni file.
	 * @param is InputStream - file za dekriptiranje
	 * @param os OutputStream - file u koji spremamo dekriptirani dokument.
	 * @param keyText kljuc
	 * @param ivector vektor
	 * @throws Exception u slucaju greske kod dekripcije.
	 */
	public void decrypt(InputStream is, OutputStream os, String keyText, 
			String ivector) throws Exception;
	
}
