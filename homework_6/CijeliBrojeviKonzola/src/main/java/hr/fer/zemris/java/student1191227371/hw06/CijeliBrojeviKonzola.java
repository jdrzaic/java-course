package hr.fer.zemris.java.student1191227371.hw06;

import hr.fer.zemris.java.student1191227371.hw06.CijeliBrojevi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Razred implementira rad s bibliotekom CijeliBrojevi.
 * Testiraju se sve funkcionalnosti te biblioteke.
 * Prima argumente preko konzole te za njih ispisuje svojstva.
 * Rezultat ispisuje na konzolu.
 * @author Jelena Drzaic
 *
 */
public class CijeliBrojeviKonzola {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * @param args argumenti komandne linije.
	 * @throws IOException u slucaju problema s ispisom/unosom.
	 */
	public static void main(String[] args) throws IOException {
		
		CijeliBrojevi test = new CijeliBrojevi();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int broj;
		while(true) {
			System.out.print("Unesite broj>");
			try {
				broj = Integer.parseInt(br.readLine());
				System.out.print("Paran: " + (test.jeParan(broj) ? "DA" : "NE") + ", ");
				System.out.print("Neparan: " + (test.jeNeparan(broj) ? "DA" : "NE") + ", ");
				System.out.print("Prost: " + (test.jeProst(broj) ? "DA" : "NE") + "\n");
			}catch(NumberFormatException e) {
				System.err.println("Unos mora biti cijeli broj.");
			}
		}
	}
}
