package com.loki2302.jsick.evaluator.errors;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.Evaluator;

public class CompositeError extends AbstractError {
	private final List<AbstractError> errors = new ArrayList<AbstractError>();
	
	public CompositeError(Evaluator<?, ?> evaluator, Object input, List<AbstractError> errors) {
		super(evaluator, input);
		this.errors.addAll(errors);
	}
	
	public List<AbstractError> getEvaluatorErrors() {
		return errors;
	}
}