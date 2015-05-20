package hr.fer.zemris.java.tecaj.hw5.db;

import hr.fer.zemris.java.tecaj.hw5.db.components.Console;
import hr.fer.zemris.java.tecaj.hw5.db.components.StudentDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Program sluzi za rad s bazom podataka s zapisima o studentima.
 * Put do tekstualnog filea s podacima dobiva sa kao argument komandne linije.
 * Zapisi su oblika "jmbag  	prezime		ime		ocjena", odvojeni tab-ovima.
 * @author Jelena Drzaic
 *
 */
public class StudentDB {
	
	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Parametri dani u nastavku;
	 * @param args argumenti komandne linije.
	 * @throws IOException u slucaju problema s ispisom/ucitavanjem.
	 */
	public static void main(String[] args) throws IOException {
		StudentDatabase database;
	
		try {
			List<String> lines = Files.readAllLines(
					Paths.get("database.txt"),
					StandardCharsets.UTF_8);
			database = new StudentDatabase(lines);
		}catch(IOException e) {
			throw new IOException("cannot open file");
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader
				(new BufferedInputStream(System.in),StandardCharsets.UTF_8));
		
		Writer ow = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Console.mainWork(br, ow, database);
	}
}
