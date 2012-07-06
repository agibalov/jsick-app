package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMDoubleConstExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.DoubleConstExpression;
import com.loki2302.jsick.expressions.TypedExpression;

public class DoubleConstExpressionEvaluator extends Evaluator<DOMDoubleConstExpression, TypedExpression> {
	@Override
	protected Context<TypedExpression> evaluateImpl(Context<DOMDoubleConstExpression> input) {
		return ok(new DoubleConstExpression(input.getValue().getValue()));
	}		
}