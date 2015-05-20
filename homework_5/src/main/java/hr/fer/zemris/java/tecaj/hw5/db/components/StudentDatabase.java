package hr.fer.zemris.java.tecaj.hw5.db.components;

import hr.fer.zemris.java.tecaj.hw5.db.filter.IFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Razred reprezentira jednostavnu bazu podataka.
 * Baza sluzi za pohranu osnovnih podataka o studentima
 * jmbag, prezime, ime, ocjena.
 * @author Jelena Drzaic
 *
 */
public class StudentDatabase {
	
	/**
	 * Lista studenata pohranjenih u bazi
	 */
	public List<StudentRecord> students;
	
	/**
	 * Index na instanci baze
	 */
	public Map<String, StudentRecord> index;
	
	/**
	 * Konstruktor razreda.
	 * prima listu stringova-podataka o studentima.
	 * Pohranjuje podatke u bazu.
	 * @param data lista stringova-zapisa o studentima
	 */
	public StudentDatabase(List<String> data) {
		students = new ArrayList<StudentRecord>();
		index = new HashMap<String, StudentRecord>();
		String[] info;
		for(int i = 0; i < data.size(); ++i) {
			info = data.get(i).split("\t");
			//student ne postoji u indexu
			if(!(index.containsKey((info[0])))){
				students.add(new StudentRecord(info[0], 
					info[1], info[2], Integer.parseInt(info[3])));
			}
			index.put(info[0], new StudentRecord(info[0], 
					info[1], info[2], Integer.parseInt(info[3])));
		}
	}
	
	/**
	 * Vraca podatke za studenta s prosijedenim JMBAG-om.
	 * @param jmbag JMBAG studenta kojeg potrazujemo.
	 * @return student s proslijedenim JMBAG-om.
	 */
	public StudentRecord forJMBAG(String jmbag) {
		if(index.containsKey(jmbag)) {
			return index.get(jmbag);
		}
		return null;
	}
	
	/**
	 * Metoda filtrira bazu podataka s obzirom na dani uvjet.
	 * Metoda vraca filtriranu bazu podataka-listu podataka o studentima 
	 * tipa StudentRecord.
	 * @param filter izraz na temelju kojeg filtriramo.
	 * @return lista studenata koji zadovoljavaju uvjete.
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> temporary = new ArrayList<StudentRecord>();
		for(int i = 0; i < students.size(); ++i) {
			if(filter.accepts(students.get(i))) {
				temporary.add(students.get(i));
			}
		}
		return temporary;
	}
}