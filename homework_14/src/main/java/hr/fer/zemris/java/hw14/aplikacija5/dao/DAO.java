package hr.fer.zemris.java.hw14.aplikacija5.dao;

import hr.fer.zemris.java.hw14.aplikacija5.model.PollEntry;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

import java.sql.Connection;
import java.util.List;

/**
 * Sučelje prema podsustavu za perzistenciju podataka.
 * 
 * @author jelena
 *
 */
public interface DAO {
	
	/**
	 * Metoda dohvaca anketu iz baze kojoj je primarni kljuc 
	 * jednak proslijedenom parametru.
	 * ako takva ne postoji, vraca null
	 * @param id primarni kljuc trazene ankete
	 * @return anketa, tipa {@link PollEntry}
	 */
	public PollEntry getPoll(long id);

	/**
	 * Metoda dohvaca sve ankete pohranjene u bazi,
	 * te ih pohranjuje u listu instanci {@link PollEntry}.
	 * @return lista anketa
	 */
	public List<PollEntry> getPolls();

	/**
	 * Metoda dodaje anketu s zadanim nazivom, opisom u bazu.
	 * Za unos koristi proslijedenu konekciju.
	 * @param title naziv ankete
	 * @param message opis ankete
	 * @param con koristena konekcija
	 * @return id dodane ankete
	 */
	public long addPoll(String title, String message, Connection con);

	/**
	 * Metoda dohvaca sve opcije ankete s primarnim kljucem 
	 * jednakim id.
	 * @param id primarni kljuc ankete
	 * @return lista opcija ankete, tipa {@link PollOptionsEntry}
	 */
	public List<PollOptionsEntry> getOptions(long id);

	/**
	 * Metoda dodaje opciju s zadanim parametrima u bazu,
	 * te je pridruzuje bazi s proslijedenim primarnim kljucem.
	 * @param title ime opcije
	 * @param link link opcije
	 * @param pollID id ankete kojoj dodajemo opciju
	 * @param votes broj glasova za opciju koju dodajemo
	 * @param con koristena konekcija
	 * @return id dodane opcije
	 */
	public long addOption(String title, String link, long pollID, int votes, Connection con);
	
	/**
	 * Metoda dohvaca broj glasova za opciju pohranjenu u bazi, 
	 * pod proslijedenim id-om, iz ankete s zadanim id-om.
	 * @param pollID id ankete
	 * @param optionID id opcije
	 * @return broj glasova
	 */
	public Integer getOptionVotesCount(long pollID, long optionID);

	/**
	 * Metoda osvjezava glasove za opciju s odredenim id-om, 
	 * iz ankete s odredenim id-om.
	 * @param pollID id ankete
	 * @param optionID id opcije
	 * @param count novi broj glasova
	 */
	public void updateOptionVotesCount(long pollID, long optionID, int count);
	
	/**
	 * Metoda provjerava postoji li anketa s zadanim nazivom.
	 * @param title naziv ankete
	 * @param con koristena konekcija
	 * @return true ako anketa postoji, false inace
	 */
	boolean PollExists(String title, Connection con);
}
