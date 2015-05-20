package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Razred implementira sucelje Instruction.
 * Instance razreda iniciraju prekid assemblerskog programa.
 * @author Jelena Drzaic
 *
 */
public class InstrHalt implements Instruction {

	/**
	 * Konstuktor razreda.
	 * Kao argument ocekuje praznu listu.
	 * U suprotnom, izbacuje IllegalArgumentException.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrHalt(List<InstructionArgument> arguments) {
		if(arguments.size() > 0) {
			throw new IllegalArgumentException("Expected 0 arguments!");
		}
	}
	
	/**
	 * Metoda vraca true, tj. signalizira zavrsetak programa.
	 */
	@Override
	public boolean execute(Computer computer) {
		return true;
	}
}
