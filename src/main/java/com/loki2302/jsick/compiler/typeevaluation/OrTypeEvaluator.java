package com.loki2302.jsick.compiler.typeevaluation;

public class OrTypeEvaluator implements TypeEvaluator {

	private final TypeEvaluator[] evaluators;
	
	public OrTypeEvaluator(TypeEvaluator[] evaluators) {
		this.evaluators = evaluators;
	}
	
	@Override
	public TypeEvaluationResult evaluate(EvaluationContext evaluationContext) {
		
		for(TypeEvaluator evaluator : evaluators) {
			TypeEvaluationResult result = evaluator.evaluate(evaluationContext);
			if(result.isOk()) {
				return result;
			}
		}
		
		return TypeEvaluationResult.fail(new NoViableAlternativeTypeEvaluationError(this, evaluationContext, evaluators));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Or{");
		for(TypeEvaluator evaluator : evaluators) {
			sb.append(evaluator);
			sb.append(",");
		}
		sb.append("}");
		
		return sb.toString();
	}
}
