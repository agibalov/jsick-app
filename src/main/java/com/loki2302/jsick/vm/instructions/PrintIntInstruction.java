package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class PrintIntInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.printInt();		
	}
	
	@Override
	public String toString() {
		return String.format("print.i");
	}

}
