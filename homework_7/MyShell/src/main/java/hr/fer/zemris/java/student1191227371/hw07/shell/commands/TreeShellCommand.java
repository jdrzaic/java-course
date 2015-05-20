package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira sucelje ShellCommand
 * Reprezentira naredbu za ispis(rekurzivni) strukture direktorija
 * od odredenog direktorija
 * @author jelena Drzaic
 *
 */
public class TreeShellCommand implements ShellCommand{

	/**
	 * Razred implementira sucelje FileVisitor
	 * Sluzi za indentirani ispis obidenih direktorija
	 * @author jelena Drzaic
	 *
	 */
private static class IndentiraniIspis implements FileVisitor<Path> {

		/**
		 * Broj znakova za koje indentiramo ispis
		 */
		private int indentLevel;


		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			if(indentLevel == 0) {
				System.out.println(dir);
			}else {
				System.out.printf("%s", dir.getFileName());
			}	
			indentLevel +=2;
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			System.out.printf("%" + indentLevel + "s%s%n", "",file.getFileName());			
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc)
				throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc)
				throws IOException {
			indentLevel -= 2;
			return FileVisitResult.CONTINUE;
		}
		
	}
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
	 public TreeShellCommand() {
		arguments = null;
	}

	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		Path p = Paths.get(arguments.get(0));
		try {
			Files.walkFileTree(p, new IndentiraniIspis());
		} catch (IOException e1) {
			System.err.println("Unable to walk file tree");
			e1.printStackTrace();
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "tree";
	}

	@Override
	public List<String> getCommandDescription() {
		return arguments;
	}

	
}
