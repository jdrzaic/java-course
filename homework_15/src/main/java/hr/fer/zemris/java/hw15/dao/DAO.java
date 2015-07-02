package hr.fer.zemris.java.hw15.dao;

import java.util.List;

import hr.fer.zemris.java.tecaj_14.model.BlogComment;
import hr.fer.zemris.java.tecaj_14.model.BlogEntry;
import hr.fer.zemris.java.tecaj_14.model.BlogUser;

/**
 * Interface za perzistenciju podataka.
 * @author jelena
 *
 */
public interface DAO {

	/**
	 * Metoda dohvaća entry sa zadanim <code>id</code>-em. Ako takav entry ne postoji,
	 * vraća <code>null</code>.
	 * @param id ključ zapisa
	 * @return entry ili <code>null</code> ako entry ne postoji
	 * @throws DAOException ako dođe do pogreške pri dohvatu podataka
	 */
	public BlogEntry getBlogEntry(Long id) throws DAOException;
	
	/**
	 * Metoda dohvaca sve blog entry-je proslijedenog usera.
	 * @param user user cije entry-je dohvacamo
	 * @return lista instacni {@link BlogEntry}
	 * @throws DAOException kod problema s dohvatom informacija
	 */
	public List<BlogEntry> getBlogEntries(BlogUser user) throws DAOException;
	
	/**
	 * Metoda za zadani nick dohvaca odgovarajuci {@link BlogUser}.
	 * ako takav ne postoji, vraca null.
	 * @param nickname nick trazenog {@link BlogUser}
	 * @return {@link BlogUser} s zadanim nick-om, ili null ako takav ne postoji
	 * @throws DAOException kod problema s dohvatom informacija
	 */
	public BlogUser getBlogUser(String nickname) throws DAOException;
	
	/**
	 * Metoda dohvaca sve postojece {@link BlogUser}-e.
	 * @return lista postojecih {@link BlogUser}-a.
	 * @throws DAOException kod problema s dohvatom informacija
	 */
	public List<BlogUser> getBlogUsers() throws DAOException;
	
	/**
	 * Metoda dohvaca sve komentare koji su dodani na proslijedeni {@link BlogEntry}.
	 * @param entry {@link BlogEntry} cije komentare dohvacamo
	 * @return lista {@link BlogComment}
	 * @throws DAOException kod problema s dohvatom informacija
	 */
	public List<BlogComment> getBlogComments(BlogEntry entry) throws DAOException;
	
	/**
	 * Metoda dodaje novog usera, na nacin odreden 
	 * ovisno o konkretnom {@link DAOProvider}-u.
	 * @param user instanca {@link BlogUser} kojeg kreiramo.
	 * @throws DAOException kod problema s kreiranjem
	 */
	public void createBlogUser(BlogUser user) throws DAOException;
	
	/**
	 * Metoda dodaje novi unos bloga, na nacin odreden konkretnom implementacijom
	 * sucelja.
	 * @param entry instanca iz koje kreiramo unos bloga
	 * @throws DAOException kod problema s kreiranjem
	 */
	public void createBlogEntry(BlogEntry entry) throws DAOException;
	
	/**
	 * Metoda dodaje novi komentar na unos bloga, na nacin odreden konkretnom implementacijom sucelja.
	 * @param comment komentar koji dodajemo
	 * @throws DAOException kod problema s kreiranjem komentara
	 */
	public void createBlogComment(BlogComment comment) throws DAOException;
	
}
