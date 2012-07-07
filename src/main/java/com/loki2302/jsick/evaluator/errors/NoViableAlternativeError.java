package com.loki2302.jsick.evaluator.errors;

import java.util.List;

import com.google.common.base.Joiner;
import com.loki2302.jsick.evaluator.Evaluator;

public class NoViableAlternativeError extends CompositeError {	
	public NoViableAlternativeError(Evaluator<?, ?> evaluator, Object input, List<AbstractError> errors) {
		super(evaluator, input, errors);
	}	
	
	@Override
	public String toString() {		
		return String.format("NoViableAlternative{%s}", Joiner.on(',').join(getEvaluatorErrors()));
	}
}