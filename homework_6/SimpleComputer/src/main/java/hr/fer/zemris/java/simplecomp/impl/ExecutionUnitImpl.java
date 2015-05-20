package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.ExecutionUnit;
import hr.fer.zemris.java.simplecomp.models.Instruction;

/**
 * Razred implementira sucelje {@link ExecutionUnit}
 * @author Jelena Drzaic
 *
 */
public class ExecutionUnitImpl implements ExecutionUnit{

	@Override
	public boolean go(Computer computer) {
		try {
			computer.getRegisters().setProgramCounter(0);
			while(true) {
				Instruction instr = (Instruction)computer.getMemory().getLocation(computer.getRegisters().
						getProgramCounter());
				computer.getRegisters().incrementProgramCounter();
				if(instr.execute(computer)) {
					break;
				}
			}
			return true;
		}catch(Exception e) {
			return false;
		} 
	}
}
