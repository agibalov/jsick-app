package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.compiler.model.expressions.Expression;

public class UnknownExpressionClassCompilationError extends CompilationError {
	
	private final Expression expression;
	
	public UnknownExpressionClassCompilationError(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		return String.format("Unknown expression class: %s", expression);
	}

}
