package hr.fer.zemris.java.hw13.aplikacija2.voting.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Razred nudi staticke metode za citanje podataka o bendovima,
 * citanje podataka o glasovima, updateanje glasova, te kreiranje rezultata
 * u obliku koristenom u aplikaciji.
 * @author jelena
 *
 */
public class BandsUtility {

	/**
	 * Metoda cita podatke o bendovima iz datoteke, 
	 * te vraca mapu u kojoj mapira podatke o bendovima,tipa {@link Band},
	 * na njegov id.
	 * @param file datoteka iz koje citamo
	 * @return mapa podatka o ucitanim bendovima
	 * @throws IOException kod greske kod ucitavanja
	 */
	public synchronized static TreeMap<Integer, Band> readBands(String file) throws IOException{
		
		BufferedReader br = null;
		TreeMap<Integer, Band> bands = new TreeMap<Integer, Band>();
		try {
			br = new BufferedReader(new FileReader(file));

			String line;
			while((line = br.readLine()) != null)  {
				line = line.trim();
				//odvojeni tabom
				String[] info = line.split("\\t");
				bands.put(Integer.parseInt(info[0]),new Band(info[1], info[2]));
			}
			return bands;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * Metoda ucitava podatke o glasovima iz datoteke, updatea glasove
	 * za bend s id-om id, ako je proslijedeni parametar za id razlicit od 
	 * null.
	 * @param fileName datoteka iz koje ucitavamo
	 * @param id id benda za kojeg je potreban update glasova
	 * @return podaci o glasovima
	 */
	public synchronized static TreeMap<String, Integer> readVotes(String fileName, String id) {
		TreeMap<String, Integer> votes = new TreeMap<String, Integer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));

			String line;
			while((line = br.readLine()) != null)  {
				line = line.trim();
				String[] info = line.split("\\t");
				votes.put(info[0], Integer.parseInt(info[1]));
			}
			if(id != null) {
				votes.put(id, votes.get(id) + 1);
			}
		} catch (IOException e) {	
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
		}
		return votes;
	}
	
	/**
	 * Metoda ispisuje podatke o glasovima za bendove u 
	 * datoteku.
	 * @param votes podatci o glasovima
	 * @param fileName datoteka u koju zapisujemo
	 */
	public synchronized static void writeVotes(TreeMap<String, Integer> votes, String fileName) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(
					 new FileWriter(fileName));
			for(Entry<String, Integer> e : votes.entrySet()) {
				bw.write(e.getKey() + "\t" + e.getValue() + "\n");
			}
		} catch (IOException ignorable) {
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * Metoda iz danih datoteka generira rezultate o bendovima i 
	 * pohranjuje ih u listu instanci {@link BandResult}.
	 * @param bandsFile informacije o bendovima
	 * @param votesFile informacije o glasovima
	 * @return lista instanci {@link BandResult}
	 * @throws IOException u slucaju problema s ucitavanjem, ispisom
	 */
	public static List<BandResult> generateResults(String bandsFile, String votesFile)
			throws IOException {
		
		Map<Integer, Band> bands = readBands(bandsFile);
		
		TreeMap<String, Integer> votes = readVotes(votesFile, null);
		
		List<Map.Entry<String, Integer>> votesSorted = 
				new ArrayList<>(votes.entrySet());
		
		Collections.sort(votesSorted, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		List<BandResult> results = new ArrayList<BandResult>();
		
		for(Map.Entry<String, Integer> entry : votesSorted) {
			
			results.add(new BandResult(bands.get(Integer.parseInt(entry.getKey())).getName(),
					bands.get(Integer.parseInt(entry.getKey())).getUrl(), 
					Integer.valueOf(entry.getValue())));
		}
		return results;
	}
}
