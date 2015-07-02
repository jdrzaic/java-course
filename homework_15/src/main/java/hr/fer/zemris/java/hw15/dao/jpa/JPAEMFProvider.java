package hr.fer.zemris.java.hw15.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Razred za cuvanje i dohvat instance {@link EntityManagerFactory}-a 
 * koristenog u aplikaciji.
 * @author jelena
 *
 */
public class JPAEMFProvider {

	/**
	 * Instanca {@link EntityManagerFactory}-a koja se koristi
	 */
	public static EntityManagerFactory emf;
	
	/**
	 * Metoda dohvaca aktualnu instancu {@link EntityManagerFactory}-a.
	 * @return instanca {@link EntityManagerFactory}.
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * Metoda postavlja clansku varijablu {@link EntityManagerFactory}-a
	 * na proslijedenu vrijednost.
	 * @param emf {@link EntityManagerFactory} na kojeg postavljamo
	 */
	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}
