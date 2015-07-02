package hr.fer.zemris.java.hw15.crypto;

import java.security.MessageDigest;

/**
 * Razred sluzi za izracunavanje digesta
 * upotrebom SHA algoritma.
 * @author jelena
 *
 */
public class DigestChecker {
	
	/**
	 * Koristena instanca razreda MessageDigest
	 */
	private static MessageDigest md;
	
	/**
	 * Metoda izracunava digest koristenjem SHA algoritma,
	 * te njegovu vrijednost vraca.
	 * @param str string ciji digest racunamo
	 */
	public static String calculateSHA(String str){
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch(Exception e) {
			return null;
		}
		md.reset();
		md.update(str.getBytes());
		
		byte[] digest = md.digest();
		String ret = "";
		for(byte d : digest) {
			ret += String.valueOf((int) d);
		}
		return ret;
	}
	
	/**
	 * Metoda provjerava je li digest prvog argumeta jednak
	 * drugom argumentu.
	 * @param str1 string ciji digest racunamo
	 * @param str2 digest s kojim usporedujemo
	 */
	public static boolean check(String str1, String str2){
		return calculateSHA(str1).equals(str2);
	}
}
