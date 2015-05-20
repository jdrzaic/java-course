package hr.fer.zemris.java.tecaj.hw5.db.filter.getter;

import hr.fer.zemris.java.tecaj.hw5.db.components.StudentRecord;

/**
 * Razred sluzi za dohvat polja u zapisu u studentu
 * koje sadrzi prezime studenta.
 * @author Jelena Drzaic
 *
 */
public class LastNameFieldGetter implements IFieldValueGetter {

	@Override
	public String get(StudentRecord record) {
		if(record == null) {
			throw new IllegalArgumentException("StudentRecord invalid");
		}
		return record.getLastName();
	}

}
