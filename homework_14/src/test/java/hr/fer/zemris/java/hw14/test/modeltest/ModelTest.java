package hr.fer.zemris.java.hw14.test.modeltest;

import hr.fer.zemris.java.hw14.aplikacija5.model.PollEntry;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

import org.junit.Assert;
import org.junit.Test;

/**
 * Razred testira model domene aplikacije.
 * @author jelena
 *
 */
public class ModelTest {

	/**
	 * metoda testira {@link PollEntry}.
	 */
	@Test
	public void testConstructorEntry() {
		PollEntry entry = new PollEntry(1, "anketa1", "opis ankete1");
		Assert.assertEquals(1, entry.getId());
		Assert.assertEquals("opis ankete1", entry.getMessage());
		entry.setId(5);
		Assert.assertEquals(5, entry.getId());
		Assert.assertEquals("anketa1", entry.getTitle());
		entry.setMessage("opis2");
		Assert.assertEquals("opis2", entry.getMessage());
		entry.setTitle("anketanew1");
		Assert.assertEquals("anketanew1", entry.getTitle());		
	}
	
	/**
	 * Metoda testira {@link PollOptionsEntry}
	 */
	@Test
	public void testConstructorOption() {
		PollOptionsEntry option = new PollOptionsEntry(1, "option1", "link1", 2);
		Assert.assertEquals("link1", option.getOptionLink());
		Assert.assertEquals(2, option.getVotesCount());
		option.setVotesCount(55);
		Assert.assertEquals(55, option.getVotesCount());
		option.incrementVotesCount();
		Assert.assertEquals(56, option.getVotesCount());
	}
}
