package hr.fer.zemris.java.student1191227371.hw07.crypto.digest;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Razred sluzi za racunanje i provjeru file digesta danog filea.
 * @author Jelena Drzaic
 *
 */
public class DigestChecker {
	
	/**
	 * input stream-file
	 */
	InputStream is;
	
	/**
	 * Ocekivani file digest
	 */
	String expectedDigest;
	
	/**
	 * Izracunati file digest
	 */
	String calculatedDigest;
	
	/**
	 * instanca razreda MessageDigest
	 */
	MessageDigest md;
	
	/**
	 * Konstruktor razreda.
	 * Kao argumente prima file čiju
	 * @param is
	 * @param expectedDigest
	 */
	public DigestChecker(InputStream is, String expectedDigest) {
		this.is = is;
		this.expectedDigest = expectedDigest;
		calculatedDigest = null;
	}
	
	/**
	 * Metoda izracunava file digest koristenjem zadanog algoritma,
	 * te njegovu vrijednost pohranjuje u clansku varijablu.
	 * @throws Exception u slucaju pogreske.
	 */
	private void calculate() throws IOException{
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch(Exception e) {
			System.out.println("Problem s izracunavanjem file digest-a.");
		}
        byte[] dataBytes = new byte[1024];
 
        int nread = 0; 
        while ((nread = is.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
  
		calculatedDigest = sb.toString();
	}
	
	/**
	 * Metoda provjerava ispravnost danog file digesta, tj. provjerava je li on jednak
	 * izracunatom.
	 * @return true ako se file digesti podudaraju, false inace.
	 * @throws IOException kod problema s outputom/inputom.
	 */
	public boolean check() throws IOException{
		
		if(calculatedDigest == null) {
			calculate();
		}
		if(expectedDigest.equals(calculatedDigest)) {
			return true;
		}
		return false;
	}
	
	public String getCalculatedDigest() {
		return calculatedDigest;
	}
}
