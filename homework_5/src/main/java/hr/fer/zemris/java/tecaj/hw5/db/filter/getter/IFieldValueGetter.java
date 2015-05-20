package hr.fer.zemris.java.tecaj.hw5.db.filter.getter;

import hr.fer.zemris.java.tecaj.hw5.db.components.StudentRecord;

/**
 * Sucelje sluzi za dobivanje informacija o zapisu StudentRecord.
 * Sadrzi metodu get koja vraca odredeni podatak o zapisu iz baze,
 * ovisno o implementaciji.
 * @author Jelena Drzaic
 *
 */
public interface IFieldValueGetter {
	
	/**
	 * Metoda vraca neki od podataka iz zapisa u bazi,
	 * na instanci koja joj je proslijedena
	 * @param record zapis o studentu, tipa StudentRecord,
	 * o kojem trazimo podatke.
	 * @return podatak iz zapisa, tipa String.
	 */
	public String get(StudentRecord record);
}