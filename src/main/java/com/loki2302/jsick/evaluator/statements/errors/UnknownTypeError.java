package com.loki2302.jsick.evaluator.statements.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;

public class UnknownTypeError extends AbstractError {
	
	private final String typeName;
	
	public UnknownTypeError(Evaluator<?, ?> evaluator, Object input, String typeName) {
		super(evaluator, input);
		this.typeName = typeName;
	}		
	
	@Override
	public String toString() {
		return String.format("UnknownType{%s}", typeName);
	}
}