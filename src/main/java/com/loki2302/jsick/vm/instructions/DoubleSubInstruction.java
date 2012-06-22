package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class DoubleSubInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.doubleSub();		
	}

	@Override
	public String toString() {
		return "sub.d";
	}

}
