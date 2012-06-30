package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;


public class CastTypeEvaluator extends NonLeafTypeEvaluator {
	
	private final TypeEvaluator from;
	private final TypeEvaluator to;
	
	public CastTypeEvaluator(TypeEvaluator from, TypeEvaluator to) {
		super(from, to);
		this.from = from;
		this.to = to;
	}

	@Override
	public TypeEvaluationResult evaluateValid(EvaluationContext evaluationContext, DependencyTypes dependencies) {
		
		Type fromType = dependencies.typeOf(from);
		Type toType = dependencies.typeOf(to);
		
		if(!fromType.canImplicitlyCastTo(toType)) {
			return TypeEvaluationResult.fail(new LeafTypeEvaluationError(this, evaluationContext));
		}
		
		return TypeEvaluationResult.ok(toType);
	}
	
	@Override
	public String toString() {
		return String.format("CastableType{%s->%s}", from, to);
	}
}
