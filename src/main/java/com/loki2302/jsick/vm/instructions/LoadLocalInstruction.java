package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class LoadLocalInstruction extends Instruction {

	private final int index;
	
	public LoadLocalInstruction(int index) {
		this.index = index;
	}
	
	@Override
	public void execute(VirtualMachine vm) {
		vm.loadLocal(index);		
	}

	@Override
	public String toString() {
		return String.format("load.%d", index);
	}
	
}
