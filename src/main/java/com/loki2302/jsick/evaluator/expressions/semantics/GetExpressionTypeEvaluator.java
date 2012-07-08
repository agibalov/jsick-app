package com.loki2302.jsick.evaluator.expressions.semantics;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class GetExpressionTypeEvaluator<TInput> extends Evaluator<TInput, Type> {
	
	private final Evaluator<TInput, ? extends Expression> expressionEvaluator;
	
	public GetExpressionTypeEvaluator(Evaluator<TInput, ? extends Expression> expressionEvaluator) {
		this.expressionEvaluator = expressionEvaluator;
	}

	@Override
	public Context<Type> evaluate(TInput input) {		
		Context<? extends Expression> expressionContext = expressionEvaluator.evaluate(input);
		if(!expressionContext.isOk()) {
			return fail(expressionContext.getError());
		}
		
		Expression expression = expressionContext.getValue();		
		
		return ok(expression.getType());
	}

}
