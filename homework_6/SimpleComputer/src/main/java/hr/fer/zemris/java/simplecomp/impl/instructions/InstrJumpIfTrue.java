package hr.fer.zemris.java.simplecomp.impl.instructions;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

import java.util.List;

/**
 * Razred implementira sucelje Instruction.
 * Instance razreda sluze za odlazak na poziciju u programu, koja
 * je pohranjena na odredenoj memorijskoj lokaciji, u slucaju da je flag 
 * postavljen na true.
 * @author Jelena Drzaic
 *
 */
public class InstrJumpIfTrue implements Instruction{
	
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
	public InstrJumpIfTrue(List<InstructionArgument> arguments) {
		if(arguments.size() != 1) {
			throw new IllegalArgumentException("Expected 1 argument!");
		}
		if(!arguments.get(0).isNumber()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		lokacija = ((Integer)arguments.get(0).getValue()).intValue();
	}

	/**
	 * Metoda postavlja ProgramCounter registar na danu lokaciju,
	 * ako je flag registar postavljen na true.
	 */
	@Override
	public boolean execute(Computer computer) {
		if(computer.getRegisters().getFlag() == true) {
			computer.getRegisters().setProgramCounter(lokacija);
		}
		return false;
	}
	
}
