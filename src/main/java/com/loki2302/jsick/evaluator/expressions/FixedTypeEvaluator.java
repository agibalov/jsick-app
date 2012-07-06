package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.types.Type;

public class FixedTypeEvaluator<TInput> extends Evaluator<TInput, Type> {
	
	private final Type type;
	
	public FixedTypeEvaluator(Type type) {
		this.type = type;
	}

	@Override
	protected Context<Type> evaluateImpl(Context<TInput> input) {		
		return ok(type);
	}

}
