package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class PrintDoubleInstruction extends Instruction {

	@Override
	public void execute(VirtualMachine vm) {
		vm.printDouble();		
	}

}
