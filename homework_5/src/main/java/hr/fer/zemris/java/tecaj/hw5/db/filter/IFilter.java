package hr.fer.zemris.java.tecaj.hw5.db.filter;
import hr.fer.zemris.java.tecaj.hw5.db.components.StudentRecord;

/**
 * Sucelje implementira metodu accepts.
 * Sluzi kako bi se provjerilo zadovoljava li neki zapis iz baze
 * dani upit.
 * @author Jelena Drzaic
 *
 */
public interface IFilter {
	
	/**
	 * Metoda provjerava zadovoljava li proslijedeni zapis iz baze
	 * odredeni upit.
	 * @param record zapis podatka iz baze.
	 * @return true ako zapis zadovoljava upit,
	 * false inace.
	 */
	public boolean accepts(StudentRecord record);
}