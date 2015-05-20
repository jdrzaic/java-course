package hr.fer.zemris.java.student1191227371.hw07.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Razred implementira sucelje Environment
 * @author Jelena Drzaic
 *
 */
public class MyEnvironment implements Environment{

	private static BufferedReader br = new BufferedReader(new 
			InputStreamReader(System.in));
	
	/**
	 * Symbol koji se ispisuje prije upisa naredbe
	 */
	private char promptSymbol;
	
	/**
	 * Symbol koji signalizira protezanje naredbe kroz vise linija
	 */
	private char morelinesSymbol;
	
	/**
	 * Symbol koji se ispisuje na pocetku svakog retka viseretcane naredbe
	 */
	private char multilinesSymbol;
	
	/**
	 * Kolekcija podrzanih naredbi
	 */
	Iterable<ShellCommand> commands;
	
	/**
	 * Konstruktor razreda
	 * @param promptSymbol simbol koji se ispisuje prije unosa naredbe
	 * @param morelinesSymbol simbol viseretcane naredbe
	 * @param multilinesSymbol simbol koji se ispisuje na pocetku viseretcane 
	 * naredbe
	 * @param commands kolekcija komandi
	 */
	public MyEnvironment(char promptSymbol, char morelinesSymbol,
			char multilinesSymbol, Iterable<ShellCommand> commands) {
		this.commands = commands;
		this.morelinesSymbol = morelinesSymbol;
		this.multilinesSymbol = multilinesSymbol;
		this.promptSymbol = promptSymbol;
	}
	
	@Override
	public String readLine() throws IOException {
		return br.readLine();
	}

	@Override
	public void write(String text) throws IOException {
		System.out.print(text);
	}

	@Override
	public void writeln(String text) throws IOException {
		System.out.println(text);
	}

	@Override
	public Iterable<ShellCommand> commands() {
		return commands;
	}

	@Override
	public Character getMultilineSymbol() {
		return multilinesSymbol;
	}

	@Override
	public void setMultilineSymbol(Character symbol) {
		this.multilinesSymbol = symbol;
	}

	@Override
	public Character getPromptSymbol() {
		return promptSymbol;
	}

	@Override
	public void setPromptSymbol(Character symbol) {
		this.promptSymbol = symbol;
	}

	@Override
	public Character getMorelinesSymbol() {
		return morelinesSymbol;
	}

	@Override
	public void setMorelinesSymbol(Character symbol) {
		this.morelinesSymbol = symbol;
	}

}
