package com.loki2302.jsick.evaluator.errors;

import java.util.List;

import com.loki2302.jsick.evaluator.Evaluator;

public class NoViableAlternativeError extends CompositeError {	
	public NoViableAlternativeError(Evaluator<?, ?> evaluator, Object input, List<AbstractError> errors) {
		super(evaluator, input, errors);
	}	
}