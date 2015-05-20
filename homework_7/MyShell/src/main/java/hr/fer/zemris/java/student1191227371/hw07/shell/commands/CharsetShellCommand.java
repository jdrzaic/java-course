package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira sucelje ShellCommand
 * Razred reprezentira naredbu za ispis dostupnih charsetova
 * @author jelena Drzaic
 *
 */
public class CharsetShellCommand implements ShellCommand{

	/**
	 * Argumenti naredbe
	 */
	List<String> arguments;
	
	/**
	 * Konstruktor razreda
	 */
	public CharsetShellCommand() {
		arguments = null;
	}
	
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.arguments = Arrays.asList(args.split("\\s+"));
		if(arguments.size() == 1 &&  !arguments.get(0).equals("") || 
				arguments.size() > 1) {
			try {
				env.writeln("Wrong arguments for this command.");
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}else {
			Set<String> charsets = Charset.availableCharsets().keySet();
			for(String charset : charsets) {
				try {
					env.writeln(charset);
				} catch (IOException e) {
					throw new RuntimeException();
				}
			}
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "charsets";
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;
	}

} 
