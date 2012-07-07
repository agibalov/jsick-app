package com.loki2302.jsick.evaluator.statements.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;

public class VariableRedefinitionError extends AbstractError {
	
	private final String variableName;
	
	public VariableRedefinitionError(Evaluator<?, ?> evaluator, Object input, String variableName) {
		super(evaluator, input);
		this.variableName = variableName;
	}		
	
	@Override
	public String toString() {
		return String.format("VariableRedefinition{%s}", variableName);
	}
}