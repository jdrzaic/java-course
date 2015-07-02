package hr.fer.zemris.java.hw15.dao.jpa;

import hr.fer.zemris.java.hw15.dao.DAOException;

import javax.persistence.EntityManager;

/**
 * Razred ima metode za dohvat {@link EntityManager}-a,
 * te sadrzi staticku instancu {@link ThreadLocal}-a koja se 
 * koristi u aplikaciji.
 * @author jelena
 *
 */
public class JPAEMProvider {

	/**
	 * Staticka instanca {@link ThreadLocal}-a, 
	 * radi s instancama {@link LocalData}
	 */
	private static ThreadLocal<LocalData> locals = new ThreadLocal<>();

	/**
	 * Metoda sluzi za dohvat {@link EntityManager}-a.
	 * @return instanca {@link EntityManager}-a
	 */
	public static EntityManager getEntityManager() {
		LocalData ldata = locals.get();
		if(ldata==null) {
			ldata = new LocalData();
			ldata.em = JPAEMFProvider.getEmf().createEntityManager();
			ldata.em.getTransaction().begin();
			locals.set(ldata);
		}
		return ldata.em;
	}

	/**
	 * Metoda sluzi za zavrsetak komunikacije s {@link EntityManager}-om.
	 * @throws DAOException u slucaju problema s izvrsenim operacijama
	 */
	public static void close() throws DAOException {
		LocalData ldata = locals.get();
		if(ldata==null) {
			return;
		}
		DAOException dex = null;
		try {
			ldata.em.getTransaction().commit();
		} catch(Exception ex) {
			dex = new DAOException("Unable to commit transaction.", ex);
		}
		try {
			ldata.em.close();
		} catch(Exception ex) {
			if(dex!=null) {
				dex = new DAOException("Unable to close entity manager.", ex);
			}
		}
		locals.remove();
		if(dex!=null) throw dex;
	}
	
	/**
	 * Razred je wrapper za instancu tipa {@link EntityManager}.
	 * @author jelena
	 *
	 */
	private static class LocalData {
		
		/**
		 * Instanca {@link EntityManager}-a
		 */
		EntityManager em;
	}
	
}
