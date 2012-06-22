package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class IntDivInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.intDiv();		
	}

	@Override
	public String toString() {
		return "div.i";
	}

}
