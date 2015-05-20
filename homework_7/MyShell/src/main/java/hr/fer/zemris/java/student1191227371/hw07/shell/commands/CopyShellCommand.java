package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

public class CopyShellCommand implements ShellCommand{

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
	 public CopyShellCommand() {
		arguments = null;
	}
	 
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		Path p1 = Paths.get(arguments.get(0));
		Path p2 = Paths.get(arguments.get(1));
		InputStream is = null;
		OutputStream os = null;
		if(!p1.toFile().isFile()) {
			System.err.println("Only files can be copied.");
			return ShellStatus.CONTINUE;
		}
		if(p2.toFile().isDirectory()) {
			p2 = Paths.get(p2.toFile().getAbsolutePath() + "/" + p1.toFile().getName());
			try {
				Files.createFile(p2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(p2.toFile().isFile()) {
			try {
				e.writeln("Do you want to overwrite existing file?");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				if(!e.readLine().equals("Y")) {
					return ShellStatus.CONTINUE;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			is = new FileInputStream(p1.toFile());
			os = new FileOutputStream(p2.toFile());
			byte[] buff = new byte[1024];
			while(true) {
				int r = is.read(buff);
				if(r<1) break;
				os.write(buff);
			}
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if(is != null) {
				try { is.close(); } catch(IOException ignorable) {}
			}
			if(os != null) {
				try { os.close(); } catch(IOException ignorable) {}
			}
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "copy";
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;
	}

}
