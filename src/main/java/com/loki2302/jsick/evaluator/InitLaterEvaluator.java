package com.loki2302.jsick.evaluator;

public class InitLaterEvaluator<TInput, TOutput> extends Evaluator<TInput, TOutput> {
	
	private Evaluator<TInput, TOutput> evaluator;
	
	public void setEvaluator(Evaluator<TInput, TOutput> evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public Context<TOutput> evaluate(TInput input) {
		if(evaluator == null) {
			throw new RuntimeException();
		}
		
		return evaluator.evaluate(input);
	}
	
}