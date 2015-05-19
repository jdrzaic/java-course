package hr.fer.zemris.java.tecaj.hw1;

import java.text.DecimalFormat;

/**
 * Program racuna n-te korijene zadanog kompleksnog broja.
 * Agrumente prima putem naredbenog retka.
 * Argumenti su realni dio broja, imaginarni dio broja i zeljeni korijen.
 * 
 * @author Jelena Drzaic
 */

public class Roots {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Kao argumente prima sljedece;
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length != 3) {
			System.err.println("Three arguments required!");
			System.exit(1);
		}
		double realniDio = Double.parseDouble(args[0]);
		double imaginarniDio = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		double theta = Math.atan2(imaginarniDio, realniDio);
		double r = Math.sqrt(realniDio * realniDio + imaginarniDio * imaginarniDio);
		System.out.println("You requested calculation of " + n + ". roots. Solutions are:");
		korijeni(r, theta, n);
	}
	/**
	 * Metoda koja izracunava n-te korijene zadanog kompleksnog broja.
	 * Za ispis na ekran izracunatih vrijednosti koristi metodu ispis(double, double, int).
	 * 
	 * @param r modul zadanog broja
	 * @param theta kut koji zadani broj zatvara s pozitivnim dijelom x-osi (theta iz [-PI, PI])
	 * @param n zadani korijen
	 */
	static void korijeni(double r, double theta, int n) {
		
		double arg = theta / n, razlika = (2 * Math.PI) / n, realniDio, imaginarniDio, rKorijen = Math.pow(r, 1 / (double)n);
		for(int k = 0; k < n; ++k) {
			realniDio = rKorijen * Math.cos(arg);
			imaginarniDio = rKorijen * Math.sin(arg);
			ispis(realniDio, imaginarniDio, k);
			arg += razlika;
		}
	}
	/**
	 * Metoda na ekran ispisuje kompleksni broj, u obliku "k) x +- yi".
	 * Broj se ispisuje zaokruzen na dvije decimale.
	 * @param realniDio realni dio zadanog broja
	 * @param imaginarniDio imaginarni dio zadanog broja
	 * @param k pozicija broja u nekom nizu brojeva
	 */
	static void ispis(double realniDio, double imaginarniDio, int k) {
		String format = "#.###";
		DecimalFormat formatter = new DecimalFormat(format);
		if(imaginarniDio < 0) {
			System.out.println(k + 1 + ") " + formatter.format(realniDio) +
								formatter.format(imaginarniDio) + "i");			
		}
		else {
			System.out.println(k + 1 + ") " + formatter.format(realniDio) + 
								"+" + formatter.format(imaginarniDio) + "i");
		}
	}
}

