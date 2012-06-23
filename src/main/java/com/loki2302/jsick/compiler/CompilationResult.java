package com.loki2302.jsick.compiler;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.vm.instructions.Instruction;

public abstract class CompilationResult {
	
	protected final List<CompilationError> errors = new ArrayList<CompilationError>();
	protected final List<Instruction> instructions = new ArrayList<Instruction>();
	
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public List<CompilationError> getErrors() {
		return errors;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}

}
