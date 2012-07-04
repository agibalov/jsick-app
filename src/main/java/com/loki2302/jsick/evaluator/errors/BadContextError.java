package com.loki2302.jsick.evaluator.errors;

import com.loki2302.jsick.evaluator.Evaluator;

public class BadContextError extends AbstractError {
	public BadContextError(Evaluator<?, ?> evaluator, Object input) {
		super(evaluator, input);
	}		
}