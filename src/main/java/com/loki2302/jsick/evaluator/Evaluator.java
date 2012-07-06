package com.loki2302.jsick.evaluator;

import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.BadContextError;

public abstract class Evaluator<TInput, TOutput> {
	public final Context<TOutput> evaluate(Context<TInput> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		return evaluate(input.getValue());
	}
	
	public abstract Context<TOutput> evaluate(TInput input);
	
	protected Context<TOutput> ok(TOutput output) {
		return Context.<TOutput>ok(output);
	}
	
	protected Context<TOutput> fail(AbstractError error) {
		return Context.<TOutput>fail(error);
	}
}