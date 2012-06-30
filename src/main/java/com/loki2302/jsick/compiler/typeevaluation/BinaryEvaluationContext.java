package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;

public class BinaryEvaluationContext implements EvaluationContext {
	private final Type first;
	private final Type second;
	
	public BinaryEvaluationContext(Type first, Type second) {
		this.first = first;
		this.second = second;
	}
	
	public Type getFirst() {
		return first;
	}
	
	public Type getSecond() {
		return second;
	}
	
	@Override
	public String toString() {
		return String.format("BinaryContext{first=%s,second=%s}", first, second);
	}
}