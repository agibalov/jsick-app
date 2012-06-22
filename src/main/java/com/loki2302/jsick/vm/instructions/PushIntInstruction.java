package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class PushIntInstruction extends Instruction {

	private final int x;
	
	public PushIntInstruction(int x) {
		this.x = x;
	}
	
	@Override
	public void execute(VirtualMachine vm) {
		vm.pushInt(x);
	}

	@Override
	public String toString() {
		return String.format("push.i %d", x);
	}
	
}
