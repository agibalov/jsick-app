package com.loki2302.jsick.evaluator;

public class GetFirstEvaluator<T, TInput extends Tuple1<T>> extends Evaluator<TInput, T> {
	
	@Override
	public Context<T> evaluate(TInput input) {		
		Context<T> inContext = input.first;
		if(!inContext.isOk()) {
			return fail(inContext.getError());
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}