package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class GetExpressionTypeEvaluator<TInput> extends Evaluator<TInput, Type> {
	
	private final Evaluator<TInput, TypedExpression> expressionEvaluator;
	
	public GetExpressionTypeEvaluator(Evaluator<TInput, TypedExpression> expressionEvaluator) {
		this.expressionEvaluator = expressionEvaluator;
	}

	@Override
	public Context<Type> evaluate(TInput input) {		
		Context<TypedExpression> expressionContext = expressionEvaluator.evaluate(input);
		if(!expressionContext.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		TypedExpression expression = expressionContext.getValue();		
		
		return ok(expression.getType());
	}

}
