package hr.fer.zemris.java.student1191227371.hw07.crypto;

import hr.fer.zemris.java.student1191227371.hw07.crypto.decripting.AESDecryptor;
import hr.fer.zemris.java.student1191227371.hw07.crypto.decripting.Decryptor;
import hr.fer.zemris.java.student1191227371.hw07.crypto.digest.DigestChecker;
import hr.fer.zemris.java.student1191227371.hw07.crypto.encrypt.AESEncryptor;
import hr.fer.zemris.java.student1191227371.hw07.crypto.encrypt.Encryptor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Razred sluzi za dekriptiranje, kriptiranje te 
 * izracunavanje te provjeru ispravnosti file digest-a.
 * Sadrzi metodu main koja kao parametre prima kljucnu rijec:
 * 'checksha', 'encrypt' te 'decrypt' te dodatno, dokumente na kojima 
 * provodimo zadanu operaciju.
 * Daljnje upute za koristenje slijede s samim pokretanjem programa.
 * @author Jelena Drzaic
 *
 */
public class Crypto {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Argumenti u nastavku;
	 * @param args argumenti komandne linije.
	 * @throws IOException kod problema s inputom/outputom.
	 */
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if(args.length == 0) {
			System.err.println("Nema predanih argumenata.");
			System.exit(-1);
		}
		
		switch (args[0]) {
		case "checksha":
			if(args.length != 2) {
				System.err.println("Potrebno proslijediti (samo) put do filea.");
				System.exit(-2);
			}else {
				System.out.println("Please provide expected sha-256 digest for "
									+ args[1] + ":");
				System.out.print("> ");
				String expectedDigest = br.readLine();
				//put do filea
				Path path = Paths.get(args[1]);
				InputStream is = null;
				try {
					is = new BufferedInputStream(new FileInputStream(path.toFile()));
					DigestChecker checker = new DigestChecker(is, expectedDigest);
					if(checker.check()) {
						System.out.println("Digesting completed. Digest of" + args[0] + 
								" matches expected digest.");
					}else {
						System.out.println("Digesting completed. Digest of" + args[0] + "does "
								+ "not match the expected digest. Digest");
						System.out.println("was: " + checker.getCalculatedDigest());
					}
				} catch (FileNotFoundException e) {
					System.out.println("Ne postoji file");
				}finally {
					if(is != null) {
						is.close();
					}
				}
			}
			break;
		case "decrypt": 
			if(args.length != 3) {
				System.out.println("Potrebno proslijediti put do filea za dekriptiranje te"
						+ "put do output filea.");
			}else {
				Path path1 = Paths.get(args[1]);
				InputStream is = null;

				OutputStream os  = new BufferedOutputStream(new FileOutputStream(new File(args[2])));
				try {
					is = new BufferedInputStream(new FileInputStream(path1.toFile()));
					System.out.println("Please provide password as hex-encoded "
							+ "text (16 bytes, i.e. 32 hex-digits):");
					String keyText = br.readLine();
					System.out.println("Please provide initialization vector as "
							+ "hex-encoded text (32 hex-digits):");
					String ivector = br.readLine();
					Decryptor decryptor = new AESDecryptor();
					try {
						decryptor.decrypt(is, os, keyText, ivector);
						System.out.println("Decryption completed. Generated file hw07part2.pdf " + args[1] + "based on file " + args[2]);
					} catch (Exception e) {
						System.err.println("Greska kod dekriptiranja dokumenta.");
						e.printStackTrace();
					}
				}catch (FileNotFoundException e) {
					System.out.println("Ne postoji file");
				}finally {
					if(is != null) {
						is.close();
					}
					if(os != null) {
						os.close();
					}
				}
			}
			break;
		case "encrypt":
			if(args.length != 3) {
				System.out.println("Potrebno proslijediti put do filea za kriptiranje te"
						+ "put do output filea.");
			}else {
				Path path1 = Paths.get(args[1]);
				InputStream is = null;

				OutputStream os  = new BufferedOutputStream(new FileOutputStream(new File(args[2])));
				try {
					is = new BufferedInputStream(new FileInputStream(path1.toFile()));
					System.out.println("Please provide password as hex-encoded "
							+ "text (16 bytes, i.e. 32 hex-digits):");
					String keyText = br.readLine();
					System.out.println("Please provide initialization vector as "
							+ "hex-encoded text (32 hex-digits):");
					String ivector = br.readLine();
					Encryptor encryptor = new AESEncryptor();
					try {
						encryptor.encrypt(is, os, keyText, ivector);
					} catch (Exception e) {
						System.err.println("Greska kod kriptiranja dokumenta.");
						e.printStackTrace();
					}
				}catch (FileNotFoundException e) {
					System.out.println("Ne postoji file");
				}finally {
					if(is != null) {
						is.close();
					}
					if(os != null) {
						os.close();
					}
				}
			}
			break;
		default:
			System.err.println("Ne postoji zadana naredba.");
			break;
		}
	}

}
