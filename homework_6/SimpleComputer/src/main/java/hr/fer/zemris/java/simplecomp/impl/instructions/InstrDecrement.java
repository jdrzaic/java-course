package hr.fer.zemris.java.simplecomp.impl.instructions;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

import java.util.List;

/**
 * Razred implementira sucelje {@link Instruction}
 * Razred implementira instrukciju koja umanjuje vrijednost pohranjenu 
 * u registru za jedan.
 * @author Jelena Drzaic
 *
 */
public class InstrDecrement implements Instruction {
	
	private int indexRegistra;
	
	/**
	 * Konstuktor razreda.
	 * Kao argument ocekuje listu InstructionArgument-a,
	 * koja sadrzi jedan element.
	 * U suprotnom, izbacuje IllegalArgumentException
	 * Ako proslijedeni argument nije registar, izbacuje se ista iznimka.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrDecrement(List<InstructionArgument> arguments) {
		
		if(arguments.size() != 1) {
			throw new IllegalArgumentException("Expected 1 argument!");
		}
		if(!arguments.get(0).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		
		this.indexRegistra = ((Integer)arguments.get(0).getValue()).intValue();
	}
	
	/**
	 * Metoda smanjuje vrijednost pohranjenu u registru za jedan.
	 */
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getRegisters().getRegisterValue(indexRegistra);
		computer.getRegisters().setRegisterValue(indexRegistra,
			Integer.valueOf(((Integer)value).intValue() - 1));
		return false;
	}
}
