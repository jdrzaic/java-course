package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.PathParser;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred imeplementira sucelje ShellCommand
 * Reprezentira naredbu za ispis sadrzaja proslijedenog direktorija
 * @author jelena Drzaic
 *
 */
public class LsShellCommand implements ShellCommand{

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
	public LsShellCommand(){
		arguments = null;
	}
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		PathParser.parse(arguments.get(0));
		Path path = Paths.get(args);
		File[] children = path.toFile().listFiles();
		for(File child : children) {
			try {
				e.writeln(formatedOutput(child));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;
	}
	
	/**
	 * Metoda formatira ispis na zadani nacin
	 * @param file file cije informacije formatiramo
	 * @return String s informacijama o fileu
	 */
	private static String formatedOutput(File file) {
		String all = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Path pathC = Paths.get(file.getAbsolutePath());
		BasicFileAttributeView faView = Files.getFileAttributeView(
		pathC, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS
		);
		BasicFileAttributes attributes = null;
		try {
			attributes = faView.readAttributes();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileTime fileTime = attributes.creationTime();
		String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
		if(file.isDirectory()) {
			all += "d";
		}else {
			all += "-";
		}
		if(file.canRead()) {
			all += "r";
		}else {
			all += "-";
		}
		if(file.canWrite()) {
			all += "w";
		}else {
			all += "-";
		}
		if(file.canExecute()) {
			all += "x";
		}else {
			all += "-";
		}
		String length = "" + file.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 10 - length.length(); i++) {
	        sb.append(' ');
	    }
		sb.append(length);
		all += sb.toString() + " ";
		all += formattedDateTime + " ";
		all += file.getName();
		return all;
	}
}
