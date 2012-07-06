package com.loki2302.jsick.evaluator;

import com.loki2302.jsick.evaluator.errors.BadContextError;

public class GetThirdEvaluator<T, TInput extends Tuple3<?, ?, T>> extends Evaluator<TInput, T> {
	@Override
	protected Context<T> evaluateImpl(Context<TInput> input) {		
		TInput in = input.getValue();
		Context<T> inContext = in.third;
		if(!inContext.isOk()) {
			return fail(new BadContextError(this, inContext));
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}