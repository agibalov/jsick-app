package com.loki2302.jsick.compiler.typeevaluation;

public class NoViableAlternativeTypeEvaluationError extends LeafTypeEvaluationError {
	
	private final TypeEvaluator[] alternatives;

	public NoViableAlternativeTypeEvaluationError(TypeEvaluator evaluator, EvaluationContext evaluationContext, TypeEvaluator[] alternatives) {
		super(evaluator, evaluationContext);
		this.alternatives = alternatives;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NoViableAlternative{");
		for(TypeEvaluator evaluator : alternatives) {
			sb.append(evaluator);
			sb.append(",");
		}
		sb.append("}");
		
		return sb.toString();
	}
	
}