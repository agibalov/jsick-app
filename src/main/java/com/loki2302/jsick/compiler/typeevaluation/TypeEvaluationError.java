package com.loki2302.jsick.compiler.typeevaluation;

public abstract class TypeEvaluationError {
	private final TypeEvaluator evaluator;
	private final EvaluationContext evaluationContext;
	
	protected TypeEvaluationError(TypeEvaluator evaluator, EvaluationContext evaluationContext) {
		this.evaluator = evaluator;
		this.evaluationContext = evaluationContext;
	}
	
	public TypeEvaluator getTypeEvaluator() {
		return evaluator;
	}
	
	public EvaluationContext getEvaluationContext() {
		return evaluationContext;
	}
}