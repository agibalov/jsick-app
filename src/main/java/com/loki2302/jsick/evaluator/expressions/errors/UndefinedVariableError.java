package com.loki2302.jsick.evaluator.expressions.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;

public class UndefinedVariableError extends AbstractError {
	public UndefinedVariableError(Evaluator<?, ?> evaluator, Object input) {
		super(evaluator, input);
	}		
}