package com.loki2302.jsick.evaluator;

import com.loki2302.jsick.evaluator.errors.BadContextError;

public class GetFirstEvaluator<T, TInput extends Tuple1<T>> extends Evaluator<TInput, T> {
	
	@Override
	public Context<T> evaluate(TInput input) {		
		Context<T> inContext = input.first;
		if(!inContext.isOk()) {
			return fail(new BadContextError(this, inContext));
		}
		
		T inValue = inContext.getValue();		
		
		return ok(inValue);
	}    	
	
}