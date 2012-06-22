package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class SaveLocalInstruction extends Instruction {

	private final int index;
	
	public SaveLocalInstruction(int index) {
		this.index = index;
	}
	
	@Override
	public void execute(VirtualMachine vm) {
		vm.saveLocal(index);		
	}

}
