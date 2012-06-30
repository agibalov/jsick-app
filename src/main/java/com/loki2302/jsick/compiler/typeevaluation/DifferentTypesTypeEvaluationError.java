package com.loki2302.jsick.compiler.typeevaluation;

public class DifferentTypesTypeEvaluationError extends LeafTypeEvaluationError {

	public DifferentTypesTypeEvaluationError(TypeEvaluator evaluator, EvaluationContext evaluationContext) {
		super(evaluator, evaluationContext);
	}
	
	@Override
	public String toString() {
		return "DifferentTypes";
	}
	
}