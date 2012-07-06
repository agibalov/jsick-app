package com.loki2302.jsick.evaluator;


public abstract class LazyEvaluator<TInput, TOutput> extends Evaluator<TInput, TOutput> {
	private Evaluator<TInput, TOutput> evaluator;
	
	protected abstract Evaluator<TInput, TOutput> makeEvaluator();
	
	@Override
	protected Context<TOutput> evaluateImpl(Context<TInput> input) {
		if(evaluator == null) {
			evaluator = makeEvaluator();
		}
		
		return evaluator.evaluate(input);
	}
}