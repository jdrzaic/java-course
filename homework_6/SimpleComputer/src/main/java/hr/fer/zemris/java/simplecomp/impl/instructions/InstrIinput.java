package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Razred implementira sucelje Instruction.
 * Instance razreda nude unos vrijednosti tipa Integer,
 * te ih pohranjuje u memoriju racunala na lokaciju dobivenu u
 * konstruktoru.
 * @author Jelena Drzaic
 *
 */
public class InstrIinput implements Instruction {

	/**
	 * Memorijska lokacija
	 */
	int lokacija;
	
	/**
	 * Konstuktor razreda.
	 * Kao argument ocekuje listu InstructionArgument-a,
	 * koja sadrzi jedan element.
	 * U suprotnom, izbacuje IllegalArgumentException
	 * Ako proslijedeni argument nije registar, izbacuje se ista iznimka.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrIinput(List<InstructionArgument> arguments) {
		if(arguments.size() != 1) {
			throw new IllegalArgumentException("Expected 1 arguments!");
		}
		if(!arguments.get(0).isNumber()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		lokacija = ((Integer)arguments.get(0).getValue()).intValue();
	}
	
	/**
	 * Metoda ucitava broj putem konzole.
	 * Ako je broj integer, sprema ga u memoriju na zadanu lokaciju.
	 */
	@Override
	public boolean execute(Computer computer) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			Integer broj = Integer.valueOf(br.readLine());
			computer.getMemory().setLocation(lokacija, broj);
			computer.getRegisters().setFlag(true);
		}catch(Exception e) {
			computer.getRegisters().setFlag(false);
		}
		return false;
	}

}
