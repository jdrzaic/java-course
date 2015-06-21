package hr.fer.zemris.java.hw14.aplikacija5.sqldao;

import java.sql.Connection;

/**
 * Pohrana veza prema bazi podataka u ThreadLocal object. ThreadLocal je zapravo
 * mapa čiji su ključevi identifikator dretve koji radi operaciju nad mapom.
 * 
 * @author jelena
 * 
 */
public class SQLConnectionProvider {

	/**
	 * Koristena instanca {@link ThreadLocal}
	 */
	private static ThreadLocal<Connection> connections = new ThreadLocal<>();

	/**
	 * Metoda postavlja vezu za trenutnu dretvu ili brise zapis iz mape ako je argument null.
	 * @param con koristena konekcija
	 */
	public static void setConnection(Connection con) {
		if (con == null) {
			connections.remove();
		} else {
			connections.set(con);
		}
	}

	/**
	 * Metoda dohvaca konekciju koju trenutna dretva smije koristiti.
	 * @return koristena konekcija
	 */
	public static Connection getConnection() {
		return connections.get();
	}

}