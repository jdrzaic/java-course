package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Razred implementira sucelje Instruction.
 * Razred implementira instrukciju koja sluzi za pohranu podataka u
 * odredeni registar.
 * @author Jelena Drzaic
 *
 */
public class InstrLoad implements Instruction{

	/**
	 * Indeks registra
	 */
	int indexRegistra;
	
	/**
	 * Memorijska lokacija
	 */
	int memorijskaLokacija;

	/**
	 * Konstuktor razreda.
	 * Kao argument ocekuje listu InstructionArgument-a,
	 * koja sadrzi dva elementa.
	 * U suprotnom, izbacuje IllegalArgumentException
	 * Ako prvi proslijedeni argument nije registar, izbacuje se ista iznimka.
	 * Takoder, ako drugi argument nije memorijska lokacija.
	 * @param arguments lista InstructionArgument-a
	 * @throws IllegalArgumentException u slucaju opisanih poteskoca.
	 */
	public InstrLoad(List<InstructionArgument> arguments) {
		
		if(arguments.size() != 2) {
			throw new IllegalArgumentException("Expected 2 arguments!");
		}
		if(!arguments.get(0).isRegister()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		if(!arguments.get(1).isNumber()) {
			throw new IllegalArgumentException("Type mismatch for argument 0!");
		}
		
		this.indexRegistra = ((Integer)arguments.get(0).getValue()).intValue();
		this.memorijskaLokacija = ((Integer)arguments.get(1).getValue()).intValue();
	}

	/**
	 * Postavlja sadrzaj registra na vrijednost pohranjenu u memoriji na danoj lokaciji.
	 */
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getMemory().getLocation(memorijskaLokacija);
		computer.getRegisters().setRegisterValue(indexRegistra, value);
		return false;
	}
}
