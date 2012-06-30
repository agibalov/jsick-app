package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;

public class SameTypeEvaluator extends NonLeafTypeEvaluator {
	
	private final TypeEvaluator first;
	private final TypeEvaluator second;
	
	public SameTypeEvaluator(TypeEvaluator first, TypeEvaluator second) {
		super(first, second);
		this.first = first;
		this.second = second;
	}

	@Override
	public TypeEvaluationResult evaluateValid(EvaluationContext evaluationContext, DependencyTypes results) {
		Type firstType = results.typeOf(first);
		Type secondType = results.typeOf(second);
		
		if(!firstType.equals(secondType)) {
			return TypeEvaluationResult.fail(new DifferentTypesTypeEvaluationError(this, evaluationContext));
		}
		
		return TypeEvaluationResult.ok(firstType);
	}
	
	@Override
	public String toString() {
		return String.format("SameType{%s,%s}", first, second);
	}
}
