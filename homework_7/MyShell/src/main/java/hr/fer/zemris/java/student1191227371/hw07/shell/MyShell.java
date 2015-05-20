package hr.fer.zemris.java.student1191227371.hw07.shell;

import hr.fer.zemris.java.student1191227371.hw07.shell.commands.CatShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.CharsetShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.CopyShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.ExitShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.HexdumpShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.LsShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.MkdirShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.SymbolShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.commands.TreeShellCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Razred predstavlja Shell - jednostavan command prompt.
 * Podrzane naredbe su charsets, koji ispisuje dostupne charsetove,
 * ls-ispis sadrzaja nekog direktorija, cat za kopiranje datoteka,
 * symbol za promjenu simbola koji se koriste u shell-u,
 * exit za izlaz iz shell-a
 * @author Jelena Drzaic
 *
 */
public class MyShell {
		
	/**
	 * Metoda koja se poziva prilikom pokretanja programa
	 * @param args argumenti komandne linije.
	 */
	public static void main(String[] args) {
		
		Map<String, ShellCommand> commands = new HashMap<String, ShellCommand>();
		commands.put("exit", new ExitShellCommand());
		commands.put("symbol", new SymbolShellCommand());
		commands.put("ls", new LsShellCommand());
		commands.put("cat", new CatShellCommand());
		commands.put("tree", new TreeShellCommand());
		commands.put("copy", new CopyShellCommand());
		commands.put("mkdir", new MkdirShellCommand());
		commands.put("hexdump", new HexdumpShellCommand());
		
		Environment e = new MyEnvironment('>', '\\', '|', commands.values());
		
		System.out.println("Welcome to MyShell v 1.0");
		
		processSession(e, commands);
	}
	
	/**
	 * Metoda izvrsava jednu sesiju interakcije korisnika sa shell-om.
	 * @param e proslijedeni environment
	 * @param commands lista podrzanih naredbi.
	 */
	private static void processSession(Environment e, 
			Map<String, ShellCommand> commands) {
		ShellStatus status = ShellStatus.CONTINUE;
		do {
			try {
				e.write(e.getPromptSymbol() + " ");
			} catch (IOException e2) {
				System.err.println("Error wrinting on output");
			}
			String l = "";
			try {
				l = readCommand(e);
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
			String commandName = l.substring(0, l.indexOf(" "));
			String arguments = l.substring(l.indexOf(" ") + 1, l.length()).trim();
			ShellCommand command = commands.get(commandName);
			status = command.executeCommand(e, arguments);
		} while(status != ShellStatus.TERMINATE);
	}
	
	/**
	 * Metoda sluzi za dohvat jedne korisnikove naredbe.
	 * @param e proslijedeni environment
	 * @return String u kojem je pohranjen korisnikov unos naredbe.
	 * @throws IOException u slucaju problema s inputom/outputom.
	 */
	private static String readCommand(Environment e) throws IOException{
		String current;
		String all = "";
		current = e.readLine();
		if(current.endsWith("" + e.getMorelinesSymbol())) {
			all = current.substring(0, current.length() - 1);
			e.write("" + e.getMultilineSymbol() + " ");
			while((current = e.readLine()).endsWith("" + e.getMorelinesSymbol())) {
				current = current.substring(0, current.length() - 2);
				all = all + " " + current;
				e.write("" + e.getMultilineSymbol() + " ");
			}
			all = all + " " + current;
		}else {
			all = current;
		}
		return all + " ";
	}
}
