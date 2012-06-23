package com.loki2302.jsick.compiler.statements;

import java.util.List;

import com.loki2302.jsick.compiler.CompilationResult;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.vm.instructions.Instruction;

public class StatementCompilationResult extends CompilationResult {
	
	public static StatementCompilationResult error(List<CompilationError> errors) {
		StatementCompilationResult r = new StatementCompilationResult();
		r.errors.addAll(errors);
		return r;
	}
	
	public static StatementCompilationResult error(CompilationError error) {
		StatementCompilationResult r = new StatementCompilationResult();
		r.errors.add(error);
		return r;
	}
	
	public static StatementCompilationResult ok(List<Instruction> instructions) {
		StatementCompilationResult r = new StatementCompilationResult();
		r.instructions.addAll(instructions);
		return r;
	}
	
	public static StatementCompilationResult ok(Instruction instruction) {
		StatementCompilationResult r = new StatementCompilationResult();
		r.instructions.add(instruction);
		return r;
	}
	
	@Override
	public String toString() {
		if(errors.isEmpty()) {
			return String.format("Result{instructions=%d}", instructions.size());
		} 
		
		return String.format("Result{errors=%d}", errors.size());
	}
	
}