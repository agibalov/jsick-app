package com.loki2302.jsick.compiler.expressions;

import java.util.List;

import com.loki2302.jsick.compiler.CompilationResult;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.instructions.Instruction;

public class ExpressionCompilationResult extends CompilationResult {
	private JType type;
	
	public JType getType() {
		return type;
	}
	
	public static ExpressionCompilationResult error(List<CompilationError> errors) {
		ExpressionCompilationResult r = new ExpressionCompilationResult();
		r.errors.addAll(errors);
		return r;
	}
	
	public static ExpressionCompilationResult error(CompilationError error) {
		ExpressionCompilationResult r = new ExpressionCompilationResult();
		r.errors.add(error);
		return r;
	}
	
	public static ExpressionCompilationResult ok(List<Instruction> instructions, JType type) {
		ExpressionCompilationResult r = new ExpressionCompilationResult();
		r.instructions.addAll(instructions);
		r.type = type;
		return r;
	}
	
	public static ExpressionCompilationResult ok(Instruction instruction, JType type) {
		ExpressionCompilationResult r = new ExpressionCompilationResult();
		r.instructions.add(instruction);
		r.type = type;
		return r;
	}
	
	@Override
	public String toString() {
		if(errors.isEmpty()) {
			return String.format("Result{instructions=%d,type=%s}", instructions.size(), type.getName());
		} 
		
		return String.format("Result{errors=%d}", errors.size());
	}
}