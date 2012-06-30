package com.loki2302.jsick.compiler.expressions;

import java.util.List;
import java.util.Map;

import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.vm.instructions.Instruction;

public class PrecompilationResults {
	
	private final Map<Expression, ExpressionCompilationResult> results;
	
	public PrecompilationResults(Map<Expression, ExpressionCompilationResult> results) {
		this.results = results;
	}
	
	public Type getType(Expression e) {
		return results.get(e).getType();
	}
	
	public List<Instruction> getInstructions(Expression e) {
		return results.get(e).getInstructions();
	}
	
}