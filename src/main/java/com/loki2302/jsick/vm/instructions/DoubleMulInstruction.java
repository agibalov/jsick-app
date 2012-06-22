package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class DoubleMulInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.doubleMul();		
	}

	@Override
	public String toString() {
		return "mul.d";
	}

}
