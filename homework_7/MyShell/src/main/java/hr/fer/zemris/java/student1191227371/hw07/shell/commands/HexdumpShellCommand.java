package hr.fer.zemris.java.student1191227371.hw07.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import hr.fer.zemris.java.student1191227371.hw07.shell.Environment;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellCommand;
import hr.fer.zemris.java.student1191227371.hw07.shell.ShellStatus;

/**
 * Razred implementira sucelje ShellCommand
 * Reprezentira naredbu za ispis heksadekadskih ascii vrijednosti znakova
 * @author jelena Drzaic
 *
 */
public class HexdumpShellCommand implements ShellCommand{

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
	
	@Override
	public ShellStatus executeCommand(Environment env, String args) {
		this.e = env;
		this.arguments = Arrays.asList(args.split("\\s+"));
		Path p = Paths.get(arguments.get(0));
		InputStream is = null;
		int sum = 0;
		try {
			is = new FileInputStream(p.toFile());
			byte[] buff = new byte[1024];
			while(true) {
				int r = is.read(buff);
				if(r<1) break;
				for(int i = 0; i < r; ++i) {
					if (sum % 16 == 0) {
						e.write(String.format("%08X: ", sum));
					}
					e.write(String.format("%02X", buff[i]));
					++sum;
					if(sum % 16 == 0) {
						e.write(" | ");
						for(int j = sum - 16; j < sum; ++j) {
							if(buff[j] > 127 || buff[j] < 32) {
								e.write(".");
							}else {
								e.write("" + (char)buff[j]);
							}
						}
						e.write("\n");
					} else if(sum % 8 == 0) {
						e.write("|");
					} else {
						e.write(" ");
					}
				}
			}
			for (int i = sum; i % 16 != 0;) {
				e.write("  ");
				++i;
				if (i % 16 == 0) {
					e.write(" | ");
					for (int j = i - 16; j < sum; ++j) {
						if (buff[j] > 127 || buff[j] < 32) {
							e.write(".");
						} else {
							e.write("" + (char)buff[j]);
						}
					}
					e.write("\n");
				} else if (i % 8 == 0) {
					e.write("|");
				} else {
					e.write(" ");
				}
			}
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if(is!=null) {
				try { is.close(); } catch(IOException ignorable) {}
			}
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
