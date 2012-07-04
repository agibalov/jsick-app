package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.types.Type;

public class FixedTypeEvaluator<TInput> extends Evaluator<TInput, Type> {
	
	private final Type type;
	
	public FixedTypeEvaluator(Type type) {
		this.type = type;
	}

	@Override
	public Context<Type> evaluate(Context<TInput> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}	
		
		return ok(type);
	}

}
