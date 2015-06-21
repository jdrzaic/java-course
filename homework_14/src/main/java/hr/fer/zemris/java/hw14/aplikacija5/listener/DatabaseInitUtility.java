package hr.fer.zemris.java.hw14.aplikacija5.listener;

import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Razred nudi staticke metode za inicijalizaciju baze podataka.
 * @author jelena
 *
 */
public class DatabaseInitUtility {

	/**
	 * Metoda kreira tablicu u koju se spremaju opcije za
	 * dostupne ankete, naziva PollOptions.
	 * @param con koristena konekcija
	 */
	public static void createOptionsTable(Connection con) {
		
		String createString = "CREATE TABLE PollOptions" + 
				 "(id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " + 
				 "optionTitle VARCHAR(100) NOT NULL, " +
				 "optionLink VARCHAR(150) NOT NULL, " +
				 "pollID BIGINT, " +
				 "votesCount BIGINT, " +
				 "FOREIGN KEY (pollID) REFERENCES Polls(id))";
		
		
		Statement stmt = null;
	    try {
	        stmt = con.createStatement();
	        stmt.executeUpdate(createString);
	    } catch (SQLException ignorable) {} 
	    finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} }
	    }
	}

	/**
	 * Metoda kreira tablicu za pohranu informacija o dostupnim anketama.
	 * @param con Koristena konekcija
	 */
	public static void createPollsTable(Connection con) {
		
		String createString = "CREATE TABLE Polls" + 
				 "(id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " + 
				 "title VARCHAR(150) NOT NULL, " +
				 "message CLOB(2048) NOT NULL)";
		
		Statement stmt = null;
	    try {
	        stmt = con.createStatement();
	        stmt.executeUpdate(createString);
	    } catch (SQLException ignorable) {} 
	    finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} }
	    }
	}
	
	/**
	 * Metoda dohvaca argumente iz .properties filea, 
	 * te ih pohranjuje u mapu.
	 * @param path put do filea
	 * @return mapa s argumentima iz filea
	 * @throws RuntimeException u slucaju problema s citanjem iz filea
	 */
	public static Map<String, String> loadProperties(String path) throws RuntimeException{
		Map<String, String> props = new HashMap<String, String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
	        String line;

	        while ((line = br.readLine()) != null) {
	            String[] params = line.trim().split("=");
	            props.put(params[0].trim(), params[1].trim());
	        }
	    } catch (IOException e) {
			throw new RuntimeException();
		}
		//strgan properties
		if(!props.containsKey("name") || !props.containsKey("port") || 
				!props.containsKey("host") || !props.containsKey("user") ||
				!props.containsKey("password"))  {
			throw new RuntimeException();
		}
		return props;
	}
	
	/**
	 * Metoda popunjava tablice Polls i PollOptions, s inicijalnim 
	 * podacima, ako oni ne postoje u bazi.
	 * @param con konekcija koja se koristi
	 */
	public static void fillTables(Connection con) {
		//anketa o bendovima
		if (!DAOProvider.getDao().PollExists("Glasanje za omiljeni bend:", con)) {
			long id = DAOProvider.getDao().addPoll(
					"Glasanje za omiljeni bend:",
					"Od sljedećih bendova koji vam je najdraži? Kliknite na link kako biste glasali!",
					con
			);
			if (id  != -1) {
				DAOProvider.getDao().addOption(
						"The Four Seasons", "http://www.geocities.com/~goldenoldies/BigGirlsDontCry-FourSeasons.mid", Long.valueOf(id), 4,con);
				DAOProvider.getDao().addOption("The Marcels", "http://www.geocities.com/~goldenoldies/Bluemoon-Marcels.mid", Long.valueOf(id), 1,con);
				DAOProvider.getDao().addOption("The Beatles", 
						"http://www.geocities.com/~goldenoldies/TwistAndShout-Beatles.mid", Long.valueOf(id), 44,con);
				DAOProvider.getDao().addOption("The Platters",
						"http://www.geocities.com/~goldenoldies/SmokeGetsInYourEyes-Platters-ver2.mid", Long.valueOf(id), 70,con);
				DAOProvider.getDao().addOption("The Beach Boys",
						"http://www.geocities.com/~goldenoldies/SurfinUSA-BeachBoys.mid", Long.valueOf(id), 50, con);
				DAOProvider.getDao().addOption("The Everly Brothers",
						"http://www.geocities.com/~goldenoldies/All.I.HaveToDoIsDream-EverlyBrothers.mid", 
						Long.valueOf(id), 45, con);
				DAOProvider.getDao().addOption("The Mamas And The Papas",
						"http://www.geocities.com/~goldenoldies/CaliforniaDreaming-Mamas-Papas.mid", 
						Long.valueOf(id), 1, con);
			}
		}
		//anketa o filmovima
		if (!DAOProvider.getDao().PollExists("Glasanje za omiljeni film:", con)) {
			long id = DAOProvider.getDao().addPoll(
					"Glasanje za omiljeni film:",
					"Od sljedećih filmova koji vam je najdraži? Kliknite na link kako biste glasali!",
					con
			);
			if (id  != -1) {
				DAOProvider.getDao().addOption("Interstellar", 
						"http://www.imdb.com/title/tt0816692/", Long.valueOf(id), 4,con);
				DAOProvider.getDao().addOption("Boyhood", 
						"http://www.imdb.com/title/tt1065073/", Long.valueOf(id), 1,con);
				DAOProvider.getDao().addOption("Hotel grand Budapest", 
						"http://www.imdb.com/title/tt2278388/", Long.valueOf(id), 44,con);
				DAOProvider.getDao().addOption("The Fault in our Stars",
						"http://www.imdb.com/title/tt2582846/", Long.valueOf(id), 70,con);
			}
		}
	}
}
