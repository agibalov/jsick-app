package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public class PushDoubleInstruction extends Instruction {

	private final double x;
	
	public PushDoubleInstruction(double x) {
		this.x = x;
	}
	
	@Override
	public void execute(VirtualMachine vm) {
		vm.pushDouble(x);
	}

	@Override
	public String toString() {
		return String.format("push.d %f", x);
	}

}
