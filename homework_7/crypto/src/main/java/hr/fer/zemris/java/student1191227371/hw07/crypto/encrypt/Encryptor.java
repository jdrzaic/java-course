package hr.fer.zemris.java.student1191227371.hw07.crypto.encrypt;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Sucelje cije implementacije reprezentiraju kriptiranje dokumenata putem
 * odredenog algoritma za kriptiranje.
 * @author Jelena Drzaic
 *
 */
public interface Encryptor {
	
	/**
	 * Metoda sluzi za enkripciju dokumenta
	 * @param is InputStream - dokument koji kriptiramo
	 * @param os OutputStream - dokument u koji spremamo kriptirani sadrzaj.
	 * @param keyText kljuv
	 * @param ivText vektor
	 * @throws Exception u slucaju pogreske tijekom kriptiranja.
	 */
	public void encrypt(InputStream is, OutputStream os, 
			String keyText, String ivText) throws Exception;
}
