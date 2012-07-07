package com.loki2302.jsick.evaluator;

public class GetFirstEvaluator<T, TInput extends Tuple1<T>> extends Evaluator<TInput, T> {
	
	@Override
	public Context<T> evaluate(TInput input) {
		return ok(input.first);
	}    	
	
}