package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class IntSubInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.intSub();		
	}

	@Override
	public String toString() {
		return "sub.i";
	}

}