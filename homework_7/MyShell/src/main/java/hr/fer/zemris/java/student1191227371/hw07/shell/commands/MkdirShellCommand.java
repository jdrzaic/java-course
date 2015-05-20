package hr.fer.zemris.java.student1191227371.hw07.shell.commands;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira succelje ShellCommand
 * Reprezentira naredbu za stvaranje novog direktorija.
 * @author jelena Drzaic
 *
 */
public class MkdirShellCommand implements ShellCommand{


	/**
	 * Argumenti naredbe
	 */
	private List<String> arguments;
	
	/**
	 * Koristeni environment
	 */
	Environment e;
	
	/**
	 * Konstruktor razreda
	 */
	 public MkdirShellCommand() {
		arguments = null;
	}
	 
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		Path p = Paths.get(arguments.get(0));
		try {
			Files.createDirectory(p);
		} catch (IOException e1) {
			System.err.println("Unable to create directory.");
			e1.printStackTrace();
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "mkdir";
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;	
	}

}
