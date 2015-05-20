package hr.fer.zemris.java.student1191227371.hw07.crypto;

import javax.xml.bind.DatatypeConverter;

/**
 * Razred sluzi za konvertiranje tipova podataka
 * @author Jelena Drzaic
 *
 */
public class Converter {

	/**
	 * Metoda pretvara String koji sadrzi heksadekadski zapis u
	 * polje bitova
	 * @param keyText string s heksadekadskim zapisom
	 * @return polje bajtova
	 */
	public static byte[] hextobyte(String keyText) {
		
	    /*int len = keyText.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(keyText.charAt(i), 16) << 4)
	                             + Character.digit(keyText.charAt(i+1), 16));
	    }*/
	    //System.out.println(DatatypeConverter.parseHexBinary(keyText));
	    return DatatypeConverter.parseHexBinary(keyText);
	}
}
