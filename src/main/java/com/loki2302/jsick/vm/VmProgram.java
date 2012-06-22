package com.loki2302.jsick.vm;

import java.util.List;

import com.loki2302.jsick.vm.instructions.Instruction;

public class VmProgram {
	
	private final List<Instruction> instructions;
	
	public VmProgram(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	public void execute(VirtualMachine vm) {
		for(Instruction instruction : instructions) {
			instruction.execute(vm);
		}
	}
	
	public void dump() {
		for(Instruction instruction : instructions) {
			System.out.println(instruction);
		}
	}

}
