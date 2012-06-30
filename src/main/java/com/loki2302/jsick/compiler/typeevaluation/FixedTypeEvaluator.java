package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;

public class FixedTypeEvaluator implements TypeEvaluator {

	private final Type type;
	
	public FixedTypeEvaluator(Type type) {
		this.type = type;
	}
	
	@Override
	public TypeEvaluationResult evaluate(EvaluationContext evaluationContext) {
		return TypeEvaluationResult.ok(type);
	}
	
	@Override
	public String toString() {
		return String.format("Fixed{%s}", type);
	}
}