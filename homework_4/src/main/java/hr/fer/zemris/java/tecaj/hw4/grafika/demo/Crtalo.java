package hr.fer.zemris.java.tecaj.hw4.grafika.demo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import hr.fer.zemris.java.tecaj.hw4.grafika.*;
import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable;
import hr.fer.zemris.java.tecaj.hw4.grafika.StvarateljLika;
import hr.fer.zemris.java.tecaj_3.prikaz.PrikaznikSlike;
import hr.fer.zemris.java.tecaj_3.prikaz.Slika;

/**
 * Razred implementira crtanje geometrijskih likova na sliku.
 * Kao parametre prima file sa informacijama o geometrijskim likovima te
 * dimenzije slike(npr. dat.txt 50 50)
 * @author Jelena Drzaic
 *
 */
public class Crtalo {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Parametri su dani u nastavku;
	 * @param args argumenti komandne linije.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		SimpleHashtable stvaratelji = podesi(Linija.class, Pravokutnik.class, Elipsa.class, Kvadrat.class,
				Kruznica.class);
		
		String[] definicije = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8)
				.toArray(new String[0]);
		
		GeometrijskiLik[] likovi = new GeometrijskiLik[definicije.length];
		int index = 0;
		try {	
			for(String s : definicije){
				String lik = s.substring(0, s.indexOf(" "));
				String parametri = s.substring(s.indexOf(" ") + 1, s.length());
				StvarateljLika stvaratelj = (StvarateljLika)stvaratelji.get(lik);
				likovi[index++] = stvaratelj.stvoriIzStringa(parametri);
			}
		}catch(IllegalArgumentException e) {
			System.err.println("greska kod stvaranja objekta.");
			System.err.println(e.getStackTrace()[0]);
			System.exit(-1);
		}
		Slika slika = new Slika(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		
		for(GeometrijskiLik l: likovi) {
			l.popuniLik(slika);
		}
		
		slika.nacrtajSliku(System.out);
		PrikaznikSlike.prikaziSliku(slika);
	}
	
	/**
	 * Metoda podesava stanje u tablici - ubacuje parove (nazivLika, stvaratelj);
	 * @param razredi razredi koji podrzavamo.
	 * @return tablica s gore opisanim parovima.
	 */
	private static SimpleHashtable podesi(Class<?> ... razredi) {
		SimpleHashtable stvaratelji = new SimpleHashtable();
		
		for(Class<?> razred : razredi) {
		
			try {
		
				Field field = razred.getDeclaredField("STVARATELJ");
		
				StvarateljLika stvaratelj = (StvarateljLika)field.get(null);
		
				stvaratelji.put(stvaratelj.nazivLika(), stvaratelj);
			} catch(Exception ex) {
		
				throw new RuntimeException("Nije moguće doći do stvaratelja za razred "+
					razred.getName()+".", ex);
			}
		}
		return stvaratelji;
	}
}
