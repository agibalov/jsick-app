package com.loki2302.jsick.vm.instructions;

import com.loki2302.jsick.vm.VirtualMachine;

public abstract class Instruction {
	public abstract void execute(VirtualMachine vm);
}
