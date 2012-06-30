package com.loki2302.jsick.compiler.typeevaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loki2302.jsick.types.Type;


public abstract class NonLeafTypeEvaluator implements TypeEvaluator {

	private final TypeEvaluator[] dependencies;
	
	protected NonLeafTypeEvaluator(TypeEvaluator... dependencies) {
		this.dependencies = dependencies;
	}
	
	@Override
	public TypeEvaluationResult evaluate(EvaluationContext evaluationContext) {
		Map<TypeEvaluator, TypeEvaluationResult> results = new HashMap<TypeEvaluator, TypeEvaluationResult>();
		
		for(TypeEvaluator dependency : dependencies) {
			TypeEvaluationResult result = dependency.evaluate(evaluationContext);			
			results.put(dependency, result);			
		}
		
		List<TypeEvaluationResult> badResults = new ArrayList<TypeEvaluationResult>(); 
		for(TypeEvaluator typeEvaluator : results.keySet()) {
			TypeEvaluationResult result = results.get(typeEvaluator);
			if(!result.isOk()) {
				badResults.add(result);
			}
		}
		
		if(!badResults.isEmpty()) {
			return TypeEvaluationResult.fail(new DependenciesEvaluationError(this, evaluationContext, badResults));
		}
		
		return evaluateValid(evaluationContext, new DependencyTypes(results));
	}
		
	protected abstract TypeEvaluationResult evaluateValid(EvaluationContext evaluationContext, DependencyTypes results);
	
	protected static class DependencyTypes {		
		private final Map<TypeEvaluator, TypeEvaluationResult> results;
		
		public DependencyTypes(Map<TypeEvaluator, TypeEvaluationResult> results) {
			this.results = results;
		}		
		
		public Type typeOf(TypeEvaluator typeEvaluator) {
			return results.get(typeEvaluator).getType();
		}		
	}

}
