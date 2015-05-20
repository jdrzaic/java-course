package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira sucelje ShellCommand
 * Reprezentira naredbu za izlazak iz shella
 * @author jelena Drzaic
 *
 */
public class ExitShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		return ShellStatus.TERMINATE;
	}

	@Override
	public String getCommandName() {
		return "exit";
	}

	@Override
	public List<String> getCommandDescription() {
		return null;
	}

}
