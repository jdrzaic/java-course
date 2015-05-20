package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Razred implementira sucelje Instruction.
 * Razred implementira insreukciju kojom provjeravamo jednakost sadrzaja 
 * pohranjenih u registrima.
 * @author Jelena Drzaic
 *
 */
public class InstrTestEquals implements Instruction {

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
	 * Ako proslijedeni argumenti nisu registri, izbacuje se ista iznimka.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrTestEquals(List<InstructionArgument> arguments) {
		
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
	 * Metoda postavlja flag na true ako su vrijednosti pohranjene u registrima jednake,
	 * a na false inace.
	 */
	@Override
	public boolean execute(Computer computer) {
		Object value1 = computer.getRegisters().getRegisterValue(indexRegistra1);
		Object value2 = computer.getRegisters().getRegisterValue(indexRegistra2);
		if(value1.getClass().equals(value2.getClass()) && value1.getClass().equals(Integer.class)) {
			if(((Integer)value1).equals((Integer)value2)) {
				computer.getRegisters().setFlag(true);
			}else {
				computer.getRegisters().setFlag(false);
			}
		} else if(value1.getClass().equals(value2.getClass()) && value1.getClass().equals(String.class)) {
			if(String.valueOf(value1).equals(String.valueOf(value2))) {
				computer.getRegisters().setFlag(true);
			} else {
				computer.getRegisters().setFlag(false);
			}
		}else {
			computer.getRegisters().setFlag(false);
		}
		return false;
	}

}
