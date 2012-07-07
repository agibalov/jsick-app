package com.loki2302.jsick.evaluator.expressions.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;

public class UndefinedVariableError extends AbstractError {
	
	private final String variableName;
	
	public UndefinedVariableError(Evaluator<?, ?> evaluator, Object input, String variableName) {
		super(evaluator, input);
		this.variableName = variableName;
	}		
	
	@Override
	public String toString() {
		return String.format("UndefinedVariable{%s}", variableName);
	}
}