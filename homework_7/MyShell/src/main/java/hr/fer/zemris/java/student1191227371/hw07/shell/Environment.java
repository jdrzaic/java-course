package hr.fer.zemris.java.student1191227371.hw07.shell;

import java.io.IOException;

/**
 * Razred reprezentira okruzenje shell-a implementiranog u ovom 
 * projektu.
 * @author Jelena Drzaic
 *
 */
public interface Environment {

	/**
	 * Metoda cita String na neki InputStream
	 * te vraca procitano.
	 * @return procitani string.
	 * @throws IOException u slucaju greske kod citanja.
	 */
	String readLine() throws IOException;
	
	/**
	 * Metoda pise proslijedeni String na neki OutputStream.
	 * @param text string koji pisemo.
	 * @throws IOException u slucaju greske kod ispisa.
	 */
	void write(String text) throws IOException;
	
	/**
	 * Metoda pise proslijedeni String na neki OutputStream,
	 * s prelaskom u novi red.
	 * @param text string koji pisemo.
	 * @throws IOException u slucaju greske kod ispisa.
	 */
	void writeln(String text) throws IOException;
	
	/**
	 * Metoda vraca trenutno dostupne naredbe u shell-u.
	 * @return kolekciju naredbi.
	 */
	Iterable<ShellCommand> commands();
	
	/**
	 * Vraca simbol za odvajanje viseretcanih naredbi.
	 * @return simbol viseretcanih naredbi
	 */
	Character getMultilineSymbol();
	
	/**
	 * Postavlja simbol viseretcanih naredbi na zadani znak.
	 * @param symbol zadani znak na koji postavljamo simbol.
	 */
	void setMultilineSymbol(Character symbol);
	
	/**
	 * Vraca simbol za zadavanje nove naredbe u shell-u
	 * @return simbol prompta.
	 */
	Character getPromptSymbol();
	
	/**
	 * Postavlja simbol za zadavanje nove naredbe u shell-u
	 * na zadanu vrijednost.
	 * @param symbol simbol na koju postavljamo.
	 */
	void setPromptSymbol(Character symbol);
	
	/**
	 * Dohvaca simbol koji se ispisuje u slucaju protezanja naredbe kroz vise redaka.
	 * @return simbol
	 */
	Character getMorelinesSymbol();
	
	/**
	 * Postavlja simbol koji se ispisuje u slucaju protezanja naredbe kroz 
	 * vise redaka na zadanu vrijednost
	 * @param symbol znak na koji postavljamo vrijednost.
	 */
	void setMorelinesSymbol(Character symbol);
}
