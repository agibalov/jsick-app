package com.loki2302.jsick.evaluator;

import com.loki2302.jsick.evaluator.errors.BadContextError;

public class GetSecondEvaluator<T, TInput extends Tuple2<?, T>> extends Evaluator<TInput, T> {
	
	@Override
	public Context<T> evaluate(TInput input) {		
		Context<T> inContext = input.second;
		if(!inContext.isOk()) {
			return fail(new BadContextError(this, inContext));
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}