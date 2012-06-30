package com.loki2302.jsick.compiler.typeevaluation;

public class FirstTypeEvaluationContextAccessorTypeEvaluator implements TypeEvaluator {
	
	@Override
	public TypeEvaluationResult evaluate(EvaluationContext evaluationContext) {
		if(!(evaluationContext instanceof BinaryEvaluationContext)) {
			throw new RuntimeException();
		}
		
		BinaryEvaluationContext context = (BinaryEvaluationContext)evaluationContext;
		
		return TypeEvaluationResult.ok(context.getFirst());
	}
	
	@Override
	public String toString() {
		return "first";
	}
	
}
