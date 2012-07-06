package com.loki2302.jsick.evaluator.statements.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;

public class BadInitializerExpressionError extends AbstractError {
	public BadInitializerExpressionError(Evaluator<?, ?> evaluator, Object input) {
		super(evaluator, input);
	}		
}