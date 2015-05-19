package hr.fer.zemris.java.tecaj.hw1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Program izracunava povrsinu i opseg pravokutnika zadane visine i sirine.
 * Argumente prima putem naredbenog retka.
 * Argumenti su realni dio kompleksnog broja, imaginarni dio kompleksnog broja i
 * zeljeni korijen.
 *
 * @author Jelena Drzaic
 *
 */
public class Rectangle {

	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Kao argumente prima;
	 * @param args Argumenti naredbenog retka
	 */
	public static void main(String[] args) throws IOException {

		double width = -3, height = -3;
		if(args.length == 2) {
			width = Double.parseDouble(args[0]);
			height = Double.parseDouble(args[1]);
		}
		else if(args.length == 0) {
			while(true) {
				width = upis("width");
				if(width >= 0) {
					break;
				}
				else if(width == -2) { 
					System.exit(1);
				}
			}
			while(true) {
				height = upis("height");
				if(height >= 0) {
					break;
				}
				else if(height == -2) {
					System.exit(1);
				}
			}
		}
		System.out.printf("%s%f%s%f%s%f%s%f", "You have specified a rectangle with width ",
				width, " and height ", height, ". Its area is ", povrsina(width, height),
				" and its perimeter is ", opseg(width, height));
	}

	/**
	 * Metoda izracunava opseg pravokutnika zadane visine i sirine.
	 * @param width sirina pravokutnika
	 * @param height visina pravokutnika
     * @return opseg zadanog pravokutnika
     */
	static double opseg(double width, double height) {

		return 2 * width + 2 * height;
	}

	/**
	 * Metoda izracunava povrsinu pravokutnika zadane visine i sirine.
	 * @param width sirina pravokutnika
	 * @param height visina pravokutnika
     * @return povrsina zadanog pravokutnika
     */
	static double povrsina(double width, double height) {

		return width * height;
	}
	/**
	 * Metoda sluzi za unos visine i sirine pravokutnika s tipkovnice.
     * Kao parametar prima String "width" ili "height" ovisno o svojstvu koje
     * se unosi.
     * @param string "width" ili "height"
     * @return upisanu velicinu ako je unos bio regularan, -1 ako argument nije unesen,
     * -2 ako je argument < 0 , -3 ako je nemoguce citanje s tipkovnice
	 */
	static double upis(String parametar) throws IOException {
		double velicina;
		BufferedReader reader = new BufferedReader(
		new InputStreamReader(new BufferedInputStream(System.in)));
		System.out.printf("%s%s%s", "Please provide ", parametar, ": ");
		String linija = reader.readLine();
		if(linija != null) {
			String vrijednost = linija.trim();
			if(vrijednost.isEmpty()) {
				System.out.println("Nothing was given");
				return -1;
			}
			velicina = Double.parseDouble(vrijednost);
			if(velicina < 0) {
				System.out.println(parametar + " is negative.");
				return -1;
			}
			return velicina;
		}
		else{
			return -2;
		}
	}
}
