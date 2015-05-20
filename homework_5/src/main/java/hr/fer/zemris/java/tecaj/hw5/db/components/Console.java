package hr.fer.zemris.java.tecaj.hw5.db.components;

import hr.fer.zemris.java.tecaj.hw5.db.filter.QueryFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Klasa sadrzi metode za provodenje upita na studentskoj bazi
 * podataka implementiranoj u ovom projektu.
 * @author Jelena Drzaic
 *
 */
public class Console {
	
	/**
	 * Metoda obraduje upite provedene na bazi.
	 * @param reader varijabla tipa BufferedReader
	 * @param writer varijabla tipa Writer
	 * @param database baza podataka na kojoj se vrse upiti.
	 * @throws IOException u slucaju problema s ispisom/ucitavanjem.
	 */
	public static void mainWork(BufferedReader reader, 
			Writer writer, StudentDatabase database) throws IOException {
		try {
			String query;
			writer.write("> ");
			writer.flush();
			while((query = reader.readLine()) != null) {
				try {
					processQuery(query, database, writer);
				}catch(IllegalArgumentException e){
					if(e.getMessage().equals("more than one wildcard")) {
						writer.write("more than one wildcard\n");
					}
				}
				writer.write("> ");
				writer.flush();
			}
		}catch(IOException e) {
			writer.write("error while recieving query");
		}
	}
	
	/**
	 * Metoda obraduje jedan upit proveden na bazi.
	 * @param squery upit proslijeden bazi.
	 * @param database baza podataka na kojoj se vrsi upit.
	 * @param writer varijabla tipa Writer-u njega ispisujemo.
	 * @throws IOException kod problema kod ispisa.
	 */
	public static void processQuery(String squery, 
			StudentDatabase database, Writer writer) throws IOException, 
			IllegalArgumentException{
		if(!squery.startsWith("query") && !squery.startsWith("quit")) {
			throw new IllegalArgumentException("invalid query");
		}
		if(squery.startsWith("quit")) {
			System.exit(1);
		}
		String query = squery.substring(5);
		QueryFilter filter = new QueryFilter(query);
		List<StudentRecord> filtered = database.filter(filter);
		/*upit je moguce provesti na temelju JMBAG-a*/
		if(filter.getJMBAG() != null) {
			writer.write("Using index for record retrieval\n");
			if(database.forJMBAG(filter.getJMBAG()) != null) {
				//pronaden jmbag u bazi
				filtered.clear();
				filtered.add(database.forJMBAG(filter.getJMBAG()));
			}
		}
		//postoji zapis koji zadovoljava upit
		if(filtered.size() > 0) {
			print(filtered, writer);
		}
		writer.write("Records selected: " + filtered.size() + "\n");
		writer.flush();
	}
	
	/**
	 * Metoda sluzi za ispis zapisa baze koje zadovoljavaju upit.
	 * Format ispisa jednak je zadanom u zadatku.
	 * @param records lista zapisa o studentima koje zadavoljavaju upit.
	 * @param writer varijabla tipa Writer-u njega pisemo.
	 */
	private static void print(List<StudentRecord> records, Writer writer) {
		
		//najdulje ime/prezime/jmbag
		int maxJmbag = 0;
		int maxLastName = 0;
		int maxFirstName = 0;
		
		for(int i = 0, granica = records.size(); i < granica; ++i) {
			//pronadi sirinu tablice
			int curr = records.get(i).getJmbag().length();
			maxJmbag = curr > maxJmbag ? curr : maxJmbag;
			curr = records.get(i).getLastName().length();
			maxLastName = curr > maxLastName ? curr : maxLastName;
			curr = records.get(i).getFirstName().length();
			maxFirstName = curr > maxFirstName ? curr : maxFirstName;
		}
		
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		//prva linija tablica
		builder.append("+");
		for(int i = 0; i < maxJmbag + 2; ++i) {
			builder.append("=");
		}
		builder.append("+");
		for(int i = 0; i < maxLastName + 2; ++i) {
			builder.append("=");
		}
		builder.append("+");
		for(int i = 0; i < maxFirstName + 2; ++i) {
			builder.append("=");
		}
		builder.append("+===+");
		//sadrzaj tablice
		try {
			writer.write(builder.toString());
			writer.write("\n");
			writer.flush();
			for(int i = 0; i < records.size(); ++i) {
				builder2.append("| ");
				builder2.append(records.get(i).getJmbag());
				builder2.append(" | ");
				builder2.append(records.get(i).getLastName());
				//praznine do sirine najduljeg prezimena u tablici
				for(int j = 0; j < maxLastName - records.get(i).getLastName().
						length(); ++j) {
					builder2.append(" ");
				}
				builder2.append(" | ");
				builder2.append(records.get(i).getFirstName());
				//praznine do sirine najduljeg imena u tablici
				for(int j = 0; j < maxFirstName - records.get(i).getFirstName().
						length(); ++j) {
					builder2.append(" ");
				}
				builder2.append(" | " + records.get(i).getFinalGrade() + " |");
				writer.write(builder2.toString());
				writer.write("\n");
				writer.flush();
				//ispraznimo builder2
				builder2.setLength(0);
			}
			//zadnja linija
			writer.write(builder.toString());
			writer.write("\n");
			writer.flush();
		}catch(IOException e) {
			System.err.println("error on output");
		}
	}
	

}
