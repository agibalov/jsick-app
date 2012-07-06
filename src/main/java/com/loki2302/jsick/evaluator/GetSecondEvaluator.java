package com.loki2302.jsick.evaluator;

import com.loki2302.jsick.evaluator.errors.BadContextError;

public class GetSecondEvaluator<T, TInput extends Tuple2<?, T>> extends Evaluator<TInput, T> {
	@Override
	protected Context<T> evaluateImpl(Context<TInput> input) {		
		TInput in = input.getValue();
		Context<T> inContext = in.second;
		if(!inContext.isOk()) {
			return fail(new BadContextError(this, inContext));
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}