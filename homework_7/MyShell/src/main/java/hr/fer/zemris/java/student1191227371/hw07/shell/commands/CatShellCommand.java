package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira sucelje ShellCommand.
 * Implementira naredbu za ispis sadrzaja dokumenta na konzolu.
 * @author Jelena Drzaic
 *
 */
public class CatShellCommand implements ShellCommand{

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
	 public CatShellCommand() {
		arguments = null;
	}
	 
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		BufferedReader br = null;
		if(new File(arguments.get(0)).exists()) {
			
				if(arguments.size() == 1) {
				try {
					br = new BufferedReader(new InputStreamReader(
							 			new BufferedInputStream(new FileInputStream(
							 					arguments.get(0))),Charset.defaultCharset()));
				} catch (FileNotFoundException e1) {
					System.err.println("Cannot read from file");
					e1.printStackTrace();
				}
				}else {
					try {
						br = new BufferedReader(new InputStreamReader(
								new BufferedInputStream(new FileInputStream(
										arguments.get(0))), arguments.get(1)));
					} catch (UnsupportedEncodingException e1) {
						System.err.println("Cannot read from file");
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						System.err.println("Cannot read from file");
						e1.printStackTrace();
					}

				}
			
			String redak;
			try {
				while((redak = br.readLine()) != null){
					e.writeln(redak);
				}
			} catch (IOException e1) {
				System.err.println("Cannot read from file");
				e1.printStackTrace();
			}
		}else {
			System.err.println("Unabble to print file");
		}
		try {
			if(br != null) 
				br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "cat";
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;
	}

}
