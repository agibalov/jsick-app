package com.loki2302.jsick.evaluator.statements.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;

public class UnknownTypeError extends AbstractError {
	public UnknownTypeError(Evaluator<?, ?> evaluator, Object input) {
		super(evaluator, input);
	}		
}