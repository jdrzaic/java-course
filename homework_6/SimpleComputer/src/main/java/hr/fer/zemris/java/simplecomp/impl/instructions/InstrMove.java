package hr.fer.zemris.java.simplecomp.impl.instructions;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

import java.util.List;

/**
 * Razred implementira sucelje Instruction.
 * Razred sluzi za kopiranje vrijednosti iz registra u registar.
 * @author Jelena Drzaic
 *
 */
public class InstrMove implements Instruction{
	
	/**
	 * Indeks prvog registra
	 */
	private int indexRegistra1;
	
	/**
	 * Indeks drugog registra
	 */
	private int indexRegistra2;
	

	/**
	 * Konstuktor razreda.
	 * Kao argument ocekuje listu InstructionArgument-a,
	 * koja sadrzi dva elementa.
	 * U suprotnom, izbacuje IllegalArgumentException
	 * Ako proslijedeni argumenti nije registri, izbacuje se ista iznimka.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrMove(List<InstructionArgument> arguments) {
		
		if(arguments.size() != 2) {
			throw new IllegalArgumentException("Expected 2 arguments!");
		}
		if(!arguments.get(0).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		if(!arguments.get(1).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 1!");
		}
		
		this.indexRegistra1 = ((Integer)arguments.get(0).getValue()).intValue();
		this.indexRegistra2 = ((Integer)arguments.get(1).getValue()).intValue();
	}
	
	/**
	 * Metoda kopira vrijednost iz jednog registra u drugi.
	 */
	@Override
	public boolean execute(Computer computer) {
		Object value1 = computer.getRegisters().getRegisterValue(indexRegistra2);
		computer.getRegisters().setRegisterValue(
			indexRegistra1,
			Integer.valueOf(((Integer)value1).intValue()));
		return false;
	}
}
