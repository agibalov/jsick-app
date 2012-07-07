package com.loki2302.jsick.evaluator;

public class GetSecondEvaluator<T, TInput extends Tuple2<?, T>> extends Evaluator<TInput, T> {
	
	@Override
	public Context<T> evaluate(TInput input) {		
		Context<T> inContext = input.second;
		if(!inContext.isOk()) {
			return fail(inContext.getError());
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}