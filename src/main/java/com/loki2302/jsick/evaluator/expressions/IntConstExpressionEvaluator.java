package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMIntConstExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.IntConstExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class IntConstExpressionEvaluator extends Evaluator<DOMIntConstExpression, Expression> {
	
	private final Type intType;
	
	public IntConstExpressionEvaluator(Type intType) {
		this.intType = intType;
	}
	
	@Override
	public Context<Expression> evaluate(DOMIntConstExpression input) {
		return ok(new IntConstExpression(input, intType, input.getValue()));
	}		
}