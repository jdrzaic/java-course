package hr.fer.zemris.java.tecaj.hw5.test;

import hr.fer.zemris.java.tecaj.hw5.db.StudentDB;
import hr.fer.zemris.java.tecaj.hw5.db.components.Console;
import hr.fer.zemris.java.tecaj.hw5.db.components.StudentDatabase;
import hr.fer.zemris.java.tecaj.hw5.db.components.StudentRecord;
import hr.fer.zemris.java.tecaj.hw5.db.filter.getter.FirstNameFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.filter.getter.IFieldValueGetter;
import hr.fer.zemris.java.tecaj.hw5.db.filter.getter.JMBAGFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.filter.getter.LastNameFieldGetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


/**
 * Testovi baze podataka implementirane u ovom projektu.
 * @author Jelena Drzaic
 *
 */
public class DatabaseTest {

	public StudentDatabase createBase() throws IOException  {
		StudentDatabase database;
		try {
			List<String> lines = Files.readAllLines(
					Paths.get("database.txt"),
					StandardCharsets.UTF_8);
			database = new StudentDatabase(lines);
		}catch(IOException e) {
			throw new IOException("cannot open file");
		}
		return database;
	}
	
	@Test
	public void queryTest1() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query lastName=\"Pilat\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+=======+======+===+\n"
				+ 					 "| 0000000044 | Pilat | Ivan | 5 |\n"
				+ 					 "+============+=======+======+===+\n"
				+ 				     "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTest2() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query jmbag<\"0000000002\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+===========+=======+===+\n"
				+ 					   "| 0000000001 | Akšamović | Marin | 2 |\n"
				+ 					   "+============+===========+=======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTest3() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query lastName<=\"Akšamović\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+===========+=======+===+\n"
				+ 					   "| 0000000001 | Akšamović | Marin | 2 |\n"
				+ 					   "+============+===========+=======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTest4() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query lastName=\"Akšamovi*\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+===========+=======+===+\n"
				+ 					   "| 0000000001 | Akšamović | Marin | 2 |\n"
				+ 					   "+============+===========+=======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTest5() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query lastName=\"Akšamo*ić\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+===========+=======+===+\n"
				+ 					   "| 0000000001 | Akšamović | Marin | 2 |\n"
				+ 					   "+============+===========+=======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTest6() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query lastName=\"Akša*ić\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+===========+=======+===+\n"
				+ 					   "| 0000000001 | Akšamović | Marin | 2 |\n"
				+ 					   "+============+===========+=======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	/**
	 * provjera ponasanja kada dolazi do preklapanja pocetnog i krajnjeg stringa
	 * @throws IOException
	 */
	@Test
	public void queryTest7() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query lastName=\"Akšanovi*ić\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> Records selected: 0\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	/**
	 * provjera ponasanja kada dolazi do preklapanja pocetnog i krajnjeg stringa
	 * @throws IOException
	 */
	@Test
	public void queryTestFirstName() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query firstName>=\"Željko\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+========+========+===+\n"
				+ 					   "| 0000000063 | Žabčić | Željko | 4 |\n"
				+ 					   "+============+========+========+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTestEqualsMoreWildcards() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query firstName>=\"Že**o\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> > ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTestEquals() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query jmbag=\"0000000025\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> Using index for record retrieval\n"
				+ 					   "+============+=========+======+===+\n"
				+ 					   "| 0000000025 | Katanić | Dino | 2 |\n"
				+ 					   "+============+=========+======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTestAnd() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query jmbag=\"0000000025\" and firstName=\"*\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> Using index for record retrieval\n"
				+ 					   "+============+=========+======+===+\n"
				+ 					   "| 0000000025 | Katanić | Dino | 2 |\n"
				+ 					   "+============+=========+======+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void queryTestGreater() throws IOException {
		BufferedReader br = new BufferedReader(
				new StringReader("query jmbag>\"0000000062\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+========+========+===+\n"
				+ 					   "| 0000000063 | Žabčić | Željko | 4 |\n"
				+ 					   "+============+========+========+===+\n"
				+ 				       "Records selected: 1\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	/**
	 * Testiranje ispisa tablice
	 * @throws IOException
	 */
	@Test
	public void queryTest13() throws IOException {
		BufferedReader br = new BufferedReader(
		new StringReader("query jmbag>\"0000000060\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		String expected = new String("> +============+===========+===========+===+\n"
				+ 					   "| 0000000061 | Vukojević | Renato    | 2 |\n"
				+ 					   "| 0000000062 | Zadro     | Kristijan | 3 |\n"
				+ 					   "| 0000000063 | Žabčić    | Željko    | 4 |\n"
				+ 					   "+============+===========+===========+===+\n"
				+ 					   "Records selected: 3\n> ");
		Assert.assertEquals("test failed", expected, sveZapisano);
	}
	
	@Test
	public void studentRecordTest() throws IOException {
		StudentRecord record = new StudentRecord("0000000011", "Marić", "Marko", 4);
		Assert.assertEquals("0000000011 Marko Marić 4", record.toString());
		Assert.assertTrue(record.equals(new StudentRecord("0000000011", "Marić", "Marko", 4)));
		Assert.assertFalse(record.equals(new StudentRecord("0000000511", "Marić", "Marko", 4)));
		Assert.assertFalse(record.equals("neki string"));
		Assert.assertFalse(record.equals(null));
		Assert.assertTrue(record.hashCode() > 0);
	}

	@Test
	public void queryTestDoesnotExistJmbag() throws IOException {
		BufferedReader br = new BufferedReader(
		new StringReader("query jmbag>\"0008000060\""));
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.mainWork(br, sw, database);
		String sveZapisano = sw.toString();
		Assert.assertEquals("> Records selected: 0\n> ", sveZapisano);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidKeyword() throws IOException, IllegalArgumentException {
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.processQuery("pitam jmbag>\"0008000060\"", database, sw);
	}
	
	@Test
	public void JmbagDouesNotExists() throws IOException, IllegalArgumentException {
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.processQuery("query jmbag>\"0008000560\"", database, sw);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidOperator() throws IOException, IllegalArgumentException {
		StringWriter sw = new StringWriter();
		StudentDatabase database = createBase();
		Console.processQuery("query jmbag?\"0008000560\"", database, sw);
	}
	@Test(expected=IllegalArgumentException.class)
	public void firstNameFieldGetterNull() {
		IFieldValueGetter getter = new FirstNameFieldGetter();
		getter.get(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lastNameFieldGetterNull() {
		IFieldValueGetter getter = new LastNameFieldGetter();
		getter.get(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void JMBAGFieldGetterNull() {
		IFieldValueGetter getter = new JMBAGFieldGetter();
		getter.get(null);
	}
	
}
