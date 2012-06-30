package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;

public class UnaryEvaluationContext implements EvaluationContext {
	
	private final Type one;
	
	public UnaryEvaluationContext(Type one) {
		this.one = one;
	}
	
	public Type getOne() {
		return one;
	}
	
}