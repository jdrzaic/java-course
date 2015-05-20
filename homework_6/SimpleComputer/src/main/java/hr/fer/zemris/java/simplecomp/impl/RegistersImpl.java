package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Razred implementira sucelje Registers
 * @author Jelena Drzaic
 *
 */
public class RegistersImpl implements Registers{
	
	
	/**
	 * Broj dostupnih registara
	 */
	int regsLen;
	
	/**
	 * Vrijednosti pohranjene u registrima opce namjene.
	 */
	Object[] ordRegisters;
	
	/**
	 * Programsko brojilo
	 */
	int programCounter;
	
	/**
	 * Registar zastavice
	 */
	boolean flag;
	
	/**
	 * Konstruktor razreda RegistersImpl
	 * Postavlja broj registara na regsLen
	 * @param regsLen broj registara
	 */
	public RegistersImpl(int regsLen) {
		ordRegisters = new Object[regsLen];
		for(@SuppressWarnings("unused") Object register: ordRegisters) {
			register = null;
		}
		this.regsLen = regsLen;
		programCounter = 0;
		flag = false;
	}
	
	@Override
	public Object getRegisterValue(int index) {
		if(index < 0 || index >= regsLen) {
			throw new IndexOutOfBoundsException("lokacija " + index + "nije legalna.");
		}
		return ordRegisters[index];
	}

	@Override
	public void setRegisterValue(int index, Object value) {
		if(index < 0 || index >= regsLen) {
			throw new IndexOutOfBoundsException("lokacija " + index + "nije legalna.");
		}
		ordRegisters[index] = value;
	}

	@Override
	public int getProgramCounter() {
		return programCounter;
	}

	@Override
	public void setProgramCounter(int value) {
		if(value < 0) {
			throw new IndexOutOfBoundsException("Vrijednost programskog brojaca ne move biti"
					+ "negativna.");
		}
		programCounter = value;
	}

	@Override
	public void incrementProgramCounter() {
		++programCounter;
	}

	@Override
	public boolean getFlag() {
		return flag;
	}

	@Override
	public void setFlag(boolean value) {
		flag = value;
	}

}
