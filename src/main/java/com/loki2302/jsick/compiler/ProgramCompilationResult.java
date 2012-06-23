package com.loki2302.jsick.compiler;

import java.util.List;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.vm.instructions.Instruction;

public class ProgramCompilationResult extends CompilationResult {
	
	public static ProgramCompilationResult error(List<CompilationError> errors) {
		ProgramCompilationResult r = new ProgramCompilationResult();
		r.errors.addAll(errors);
		return r;
	}
	
	public static ProgramCompilationResult error(CompilationError error) {
		ProgramCompilationResult r = new ProgramCompilationResult();
		r.errors.add(error);
		return r;
	}
	
	public static ProgramCompilationResult ok(List<Instruction> instructions) {
		ProgramCompilationResult r = new ProgramCompilationResult();
		r.instructions.addAll(instructions);
		return r;
	}
	
	public static ProgramCompilationResult ok(Instruction instruction) {
		ProgramCompilationResult r = new ProgramCompilationResult();
		r.instructions.add(instruction);
		return r;
	}
	
}