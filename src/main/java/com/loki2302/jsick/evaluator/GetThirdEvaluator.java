package com.loki2302.jsick.evaluator;

public class GetThirdEvaluator<T, TInput extends Tuple3<?, ?, T>> extends Evaluator<TInput, T> {
	
	@Override
	public Context<T> evaluate(TInput input) {		
		Context<T> inContext = input.third;
		if(!inContext.isOk()) {
			return fail(inContext.getError());
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}