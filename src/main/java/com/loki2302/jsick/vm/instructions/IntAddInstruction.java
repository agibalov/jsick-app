package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class IntAddInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.intAdd();		
	}

	@Override
	public String toString() {
		return "add.i";
	}

}
