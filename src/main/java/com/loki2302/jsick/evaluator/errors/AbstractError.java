package com.loki2302.jsick.evaluator.errors;

import com.loki2302.jsick.evaluator.Evaluator;

public abstract class AbstractError {
	private final Evaluator<?, ?> evaluator;
	private final Object input;
	
	protected AbstractError(Evaluator<?, ?> evaluator, Object input) {
		this.evaluator = evaluator;
		this.input = input;
	}
	
	public Evaluator<?, ?> getEvaluator() {
		return evaluator;
	}
	
	public Object getInput() {
		return input;
	}
}