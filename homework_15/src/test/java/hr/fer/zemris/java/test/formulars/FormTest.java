package hr.fer.zemris.java.test.formulars;

import hr.fer.zemris.java.hw15.crypto.DigestChecker;
import hr.fer.zemris.java.hw15.formulars.RegistrationForm;

import org.junit.Assert;
import org.junit.Test;

/**
 * Razred testira validaciju formulara koristenih u aplikaciji.
 * @author jelena
 *
 */
public class FormTest {

	/**
	 * Testira validan unos podataka za registraciju.
	 * Takoder, testira se funkcionalnost racunanja digesta.
	 */
	@Test
	public void testRegularFormEntry1() {
		RegistrationForm form = new RegistrationForm();
		form.setNick("nick1");
		form.setFirstName("firstname1");
		form.setLastName("lastname1");
		form.setEmail("email@mail.com");
		form.setPassword("password1");
		form.checkErrors();
		Assert.assertTrue(!form.hasErrors());
		form.setEmail("mail@gmail.com");
		form.setLastName("anic");
		form.setFirstName("ana");
		form.setNick("anana");
		form.setPassword("anana");
		Assert.assertTrue(form.getEmail().equals("mail@gmail.com"));
		Assert.assertTrue(form.getNick().equals("anana"));
		Assert.assertTrue(form.getPassword().equals(DigestChecker.calculateSHA("anana")));
		Assert.assertTrue(form.getFirstName().equals("ana"));
		Assert.assertTrue(form.getLastName().equals("anic"));
		Assert.assertTrue(DigestChecker.check("anana", form.getPassword()));

	}
	
	/**
	 * Testira validan unos podataka za registraciju.
	 */
	@Test
	public void testRegularFormEntry2() {
		RegistrationForm form = new RegistrationForm();
		form.setNick("nick2");
		form.setFirstName("firstname2");
		form.setLastName("lastname2");
		form.setEmail("il@mail.com");
		form.setPassword(Integer.toString(5555555));
		form.checkErrors();
		Assert.assertTrue(!form.hasErrors());
	}
	
	/**
	 * Testira prepoznavanje maila koji nije validan.
	 */
	@Test
	public void testUnvalidMail1() {
		RegistrationForm form = new RegistrationForm();
		form.setNick("nick2");
		form.setFirstName("firstname2");
		form.setLastName("lastname2");
		form.setEmail("ilmail.com");
		form.setPassword(Integer.toString(5555555));
		form.checkErrors();
		Assert.assertTrue(form.getError("email").equals("Valid email is required!"));
	}
	
	/**
	 * Testira prepoznavanje maila koji nije validan.
	 */
	@Test
	public void testUnvalidMail2() {
		RegistrationForm form = new RegistrationForm();
		form.setNick("nick2");
		form.setFirstName("firstname2");
		form.setLastName("lastname2");
		form.setEmail("@l");
		form.setPassword(Integer.toString(5555555));
		form.checkErrors();
		Assert.assertTrue(form.getError("email").equals("Valid email is required!"));
	}
	
	/**
	 * Testira nemogucnost praznih stringova kao podataka-ime.
	 */
	@Test
	public void testUnvalidEntryEmptyStringFirst() {
		RegistrationForm form = new RegistrationForm();
		form.setNick("nick");
		form.setFirstName("");
		form.setLastName("lastname2");
		form.setEmail("@l");
		form.setPassword(Integer.toString(5555555));
		form.checkErrors();
		Assert.assertTrue(form.getError("first.name").equals("First name is required!"));
	}
	
	/**
	 * Testira nemogucnost praznih stringova kao podataka-prezime.
	 */
	@Test
	public void testUnvalidEntryEmptyStringLast() {
		RegistrationForm form = new RegistrationForm();
		form.setNick("nick");
		form.setFirstName("firstname");
		form.setLastName("");
		form.setEmail("@l");
		form.setPassword(Integer.toString(5555555));
		form.checkErrors();
		Assert.assertTrue(form.getError("last.name").equals("Last name is required!"));
	}
	
	
}
