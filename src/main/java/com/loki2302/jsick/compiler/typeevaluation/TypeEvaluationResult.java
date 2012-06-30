package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;


public class TypeEvaluationResult {
	
	private Type type;
	private TypeEvaluationError typeEvaluationError;
	
	public Type getType() {
		return type;
	}
	
	public boolean isOk() {
		return typeEvaluationError == null;
	}
	
	public TypeEvaluationError getError() {
		return typeEvaluationError;
	}
	
	@Override
	public String toString() {
		if(isOk()) {
			return String.format("EvaluationResult{type=%s}", type.getName());
		}
		
		return String.format("EvaluationResult{error=%s}", typeEvaluationError);
	}
	
	public static TypeEvaluationResult ok(Type type) {
		TypeEvaluationResult result = new TypeEvaluationResult();		
		result.type = type;
		return result;
	}
	
	public static TypeEvaluationResult fail(TypeEvaluationError typeEvaluationError) {
		TypeEvaluationResult result = new TypeEvaluationResult();
		result.typeEvaluationError = typeEvaluationError;
		return result;
	}
	
}