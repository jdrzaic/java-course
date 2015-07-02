package hr.fer.zemris.java.hw15.dao;

import hr.fer.zemris.java.hw15.dao.jpa.JPADAOImpl;

/**
 * Razred za dohvat instance implementacije sucelja {@link DAO} 
 * koji se koristi u aplikaciji.
 * @author jelena
 *
 */
public class DAOProvider {

	/**
	 * Staticka instanca implementacije sucelja {@link DAO} koja
	 * se koristi u aplikaciji.
	 */
	private static DAO dao = new JPADAOImpl();
	
	/**
	 * Metoda vraca instacnu {@link DAO} koja se koristi.
	 * @return instanca {@link DAO}
	 */
	public static DAO getDAO() {
		return dao;
	}
	
}
