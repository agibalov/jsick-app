package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class IntToDoubleInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.intToDouble();		
	}

	@Override
	public String toString() {
		return "cast.i.d";
	}
	
}
