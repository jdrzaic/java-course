package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Razred implementira sucelje {@link Instruction}
 * Razred implementira instrukciju koja ispisuje na ekran vijednost 
 * pohranjenu u registru.
 * @author Jelena Drzaic
 *
 */
public class InstrEcho implements Instruction{

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
	public InstrEcho(List<InstructionArgument> arguments) {
		
		if(arguments.size() != 1) {
			throw new IllegalArgumentException("Expected 1 arguments!");
		}
		if(!arguments.get(0).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		this.indexRegistra = ((Integer)arguments.get(0).getValue()).intValue();
	}

	/**
	 * Metoda na ekran ispisuje vrijednost zapisanu u registru.
	 */
	@Override
	public boolean execute(Computer computer) {
		System.out.print(computer.getRegisters().getRegisterValue(indexRegistra));
		return false;
	}
}
