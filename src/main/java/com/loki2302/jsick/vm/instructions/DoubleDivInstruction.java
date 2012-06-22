package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class DoubleDivInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.doubleDiv();		
	}

}
