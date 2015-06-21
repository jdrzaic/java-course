package hr.fer.zemris.java.hw14.aplikacija5.dao;

import hr.fer.zemris.java.hw14.aplikacija5.sqldao.SQLDAO;

/**
 * Singleton razred koji zna koga treba vratiti kao pružatelja usluge pristupa
 * podsustavu za perzistenciju podataka.
 * 
 * @author jelena
 * 
 */
public class DAOProvider {

	/**
	 * Instanca {@link DAO} koja se koristi u aplikaciji
	 */
	private static DAO dao = new SQLDAO();

	/**
	 * Dohvat primjerka.
	 * @return objekt koji enkapsulira pristup sloju za perzistenciju podataka
	 */
	public static DAO getDao() {
		return dao;
	}

}