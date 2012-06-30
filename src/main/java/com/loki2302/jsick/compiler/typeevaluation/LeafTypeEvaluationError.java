package com.loki2302.jsick.compiler.typeevaluation;

public class LeafTypeEvaluationError extends TypeEvaluationError {				
	public LeafTypeEvaluationError(TypeEvaluator evaluator, EvaluationContext evaluationContext) {
		super(evaluator, evaluationContext);
	}		
	
	@Override
	public String toString() {
		return String.format("Error{e=%s,c=%s}", getTypeEvaluator(), getEvaluationContext());
	}
}