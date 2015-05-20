package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira naredbu za ispis simbola koji se koristi za
 * MULTILINES/MORELINES/PROMPT. Također, putem ove naredbe mijenjamo oznake
 * za iste.
 * @author Jelena Drzaic
 *
 */
public class SymbolShellCommand implements ShellCommand{

	/**
	 * Argumenti narede
	 */
	private List<String> arguments;
	
	/**
	 * Koristeni environment
	 */
	Environment e;
	
	/**
	 * Konstruktor razreda
	 */
	public SymbolShellCommand() {
		arguments = null;
	}
	
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		if(arguments.size() == 1) {
			switch (arguments.get(0)) {
			case "MORELINES":
				try {
					e.writeln(prepareInfo("MORELINES", e.getMorelinesSymbol()));
				} catch (IOException e1) {
					throw new RuntimeException("Unable to print symbol");
				}
				break;
			case "PROMPT": 
				try {
					e.writeln(prepareInfo("PROMPT", e.getPromptSymbol()));
				} catch (IOException e1) {
					throw new RuntimeException("Unable to print symbol");
				}
				break;
			case "MULTILINE": 
				try {
					e.writeln(prepareInfo("MULTILINE", e.getMultilineSymbol()));
				} catch (IOException e1) {
					throw new RuntimeException("Unable to print symbol");
				}
				break;
			default:
				break;
			}
		}else if(arguments.size() == 2) {
			switch (arguments.get(0)) {
			case "MORELINES":
				try {
					char newSymbol = arguments.get(1).charAt(0);
					e.writeln(prepareUpdate("MORELINES", e.getMorelinesSymbol(), newSymbol));
					e.setMorelinesSymbol(newSymbol);
				} catch (IOException e1) {
					throw new RuntimeException("Unable to print symbol");
				}
				break;
			case "PROMPT": 
				try {
					char newSymbol = arguments.get(1).charAt(0);
					e.writeln(prepareUpdate("PROMPTSYMBOL", e.getMorelinesSymbol(), newSymbol));
					e.setPromptSymbol(newSymbol);
				} catch (IOException e1) {
					throw new RuntimeException("Unable to print symbol");
				}
				break;
			case "MULTILINE": 
				try {
					char newSymbol = arguments.get(1).charAt(0);
					e.writeln(prepareUpdate("MULTILINESYMBOL", e.getMultilineSymbol(), newSymbol));
					e.setMultilineSymbol(newSymbol);
				} catch (IOException e1) {
					throw new RuntimeException("Unable to print symbol");
				}
				break;
			default:
				break;
			}
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "symbol";
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;
	}

	/**
	 * Priprema string za ispis informacije o simbolima.
	 * @param keyText naredba za koju ispisujemo.
	 * @param symbol symbol trenutno koristen.
	 * @return String spreman za ispis.
	 */
	private String prepareInfo(String keyText, char symbol) {
		return "Symbol for " + keyText + " is " + "'" + symbol + "'";
	}
	
	/**
	 * Priprema string za ispis informacije o promjeni simbola 
	 * za odredenu naredbu.
	 * @param keyText naredba za koju ispisujemo.
	 * @param symbol symbol trenutno koristen.
	 * @return String spreman za ispis.
	 */
	private String prepareUpdate(String keyText, char oldSymbol, char newSymbol) {
		return "Symbol for " + keyText + "changed from " + "'" + oldSymbol + "'"
				+ "to"  +  "'" + newSymbol + "'";
	}
}
