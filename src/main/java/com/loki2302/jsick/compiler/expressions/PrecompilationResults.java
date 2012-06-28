package com.loki2302.jsick.compiler.expressions;

import java.util.Map;

import com.loki2302.jsick.compiler.model.expressions.Expression;

public class PrecompilationResults {
	
	private final Map<Expression, ExpressionCompilationResult> results;
	
	public PrecompilationResults(Map<Expression, ExpressionCompilationResult> results) {
		this.results = results;
	}
	
	public ExpressionCompilationResult getFor(Expression e) {
		return results.get(e);
	}
	
}