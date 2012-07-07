package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMDoubleConstExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.DoubleConstExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class DoubleConstExpressionEvaluator extends Evaluator<DOMDoubleConstExpression, Expression> {
	
	private final Type doubleType;
	
	public DoubleConstExpressionEvaluator(Type doubleType) {
		this.doubleType = doubleType;
	}
	
	@Override
	public Context<Expression> evaluate(DOMDoubleConstExpression input) {
		return ok(new DoubleConstExpression(doubleType, input.getValue()));
	}		
}