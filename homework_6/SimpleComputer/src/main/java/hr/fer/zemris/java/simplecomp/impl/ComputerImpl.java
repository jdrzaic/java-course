package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Memory;
import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Razred implementira sucelje Computer.
 * Racunalo sadrzi podatke o odgovarajucim registrima te memoriji.
 * 
 * @author Jelena Drzaic
 *
 */
public class ComputerImpl implements Computer{

	/**
	 * Registri racunala
	 */
	private Registers registers;
	
	/**
	 * Memorija racunala
	 */
	private Memory memory;
	
	/**
	 * Konstruktor razreda ComputerImpl
	 * Stvara racunalo s memoryLen mjesta u memoriji, te nOfReg registara.
	 * @param memoryLen velicina memorije
	 * @param nOfReg broj registara racunala
	 */
	public ComputerImpl(int memoryLen, int nOfReg) {
		this.memory = new MemoryImpl(memoryLen);
		this.registers = new RegistersImpl(nOfReg);
	}
	
	@Override
	public Registers getRegisters() {
		return registers;
	}

	@Override
	public Memory getMemory() {
		return memory;
	}
}
