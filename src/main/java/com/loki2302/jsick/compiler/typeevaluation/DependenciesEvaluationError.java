package com.loki2302.jsick.compiler.typeevaluation;

import java.util.List;

public class DependenciesEvaluationError extends TypeEvaluationError {
	private final List<TypeEvaluationResult> badResults;
	
	public DependenciesEvaluationError(TypeEvaluator evaluator, EvaluationContext evaluationContext, List<TypeEvaluationResult> badResults) {
		super(evaluator, evaluationContext);
		this.badResults = badResults;
	}
	
	public List<TypeEvaluationResult> getBadResults() {
		return badResults;
	}
}