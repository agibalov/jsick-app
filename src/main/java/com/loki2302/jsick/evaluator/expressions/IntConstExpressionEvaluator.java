package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMIntConstExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.IntConstExpression;
import com.loki2302.jsick.expressions.TypedExpression;

public class IntConstExpressionEvaluator extends Evaluator<DOMIntConstExpression, TypedExpression> {
	@Override
	public Context<TypedExpression> evaluate(Context<DOMIntConstExpression> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}

		return ok(new IntConstExpression(input.getValue().getValue()));
	}		
}