package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Razred implementira sucelje Instruction.
 * Razred sluzi za mnozenje sadrzaja dva registra, koji se tada pohranjuje
 * u treci registar.
 * @author Jelena Drzaic
 *
 */
public class InstrMul implements Instruction {
	
	/**
	 * Indeks prvog registra
	 */
	private int indexRegistra1;
	
	/**
	 * Indeks drugog registra
	 */
	private int indexRegistra2;
	
	/**
	 * Indeks registra u kojeg pohranjujemo umnozak
	 */
	private int indexRegistra3;
	
	/**
	 * Konstuktor razreda.
	 * Kao argument ocekuje listu InstructionArgument-a,
	 * koja sadrzi tri elementa.
	 * U suprotnom, izbacuje IllegalArgumentException
	 * Ako jedan od proslijedenih argument nije registar, izbacuje se ista iznimka.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrMul(List<InstructionArgument> arguments) {
		
		if(arguments.size()!=3) {
			throw new IllegalArgumentException("Expected 3 arguments!");
		}
		if(!arguments.get(0).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		if(!arguments.get(1).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 1!");
		}
		if(!arguments.get(2).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 2!");
		}
		
		this.indexRegistra1 = ((Integer)arguments.get(0).getValue()).intValue();
		this.indexRegistra2 = ((Integer)arguments.get(1).getValue()).intValue();
		this.indexRegistra3 = ((Integer)arguments.get(2).getValue()).intValue();
	}
	
	/**
	 * Metoda mnozi sadrzaje dvaju registara i pohranjuje dobivenu vrijednost u treci 
	 * registar.
	 */
	@Override
	public boolean execute(Computer computer) {
		Object value1 = computer.getRegisters().getRegisterValue(indexRegistra2);
		Object value2 = computer.getRegisters().getRegisterValue(indexRegistra3);
		computer.getRegisters().setRegisterValue(
			indexRegistra1,
			Integer.valueOf(((Integer)value1).intValue() * ((Integer)value2).intValue()));
		return false;
	}
}
