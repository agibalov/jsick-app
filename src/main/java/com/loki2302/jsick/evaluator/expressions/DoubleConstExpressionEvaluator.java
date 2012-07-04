package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMDoubleConstExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.DoubleConstExpression;
import com.loki2302.jsick.expressions.TypedExpression;

public class DoubleConstExpressionEvaluator extends Evaluator<DOMDoubleConstExpression, TypedExpression> {
	@Override
	public Context<TypedExpression> evaluate(Context<DOMDoubleConstExpression> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}

		return ok(new DoubleConstExpression(input.getValue().getValue()));
	}		
}