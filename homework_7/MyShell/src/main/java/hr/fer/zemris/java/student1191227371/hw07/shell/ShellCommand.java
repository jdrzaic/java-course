package hr.fer.zemris.java.student1191227371.hw07.shell;

import java.util.List;


/**
 * Sucelje reprezenira jednu naredbu koja se koristi u programu MyShell.
 * 
 * @author Jelena Drzaic
 *
 */
public interface ShellCommand {

	/**
	 * Metoda sluzi za izvrsavanje zadace koju obuhvaca zadana naredba.
	 * @param env environment na kojem se odvija program
	 * @param arguments string koji sadrzi argumenti naredbe
	 * @return TERMINATE u slucaju naredbe exit, CONTINUE inace.
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	
	/**
	 * Metoda dohvaca ime naredbe.
	 * @return ime naredbe
	 */
	String getCommandName();
	
	/**
	 * Metoda vraca listu argumenata naredbe.
	 * @return lista argumenata naredbe.
	 */
	List<String> getCommandDescription();
}
