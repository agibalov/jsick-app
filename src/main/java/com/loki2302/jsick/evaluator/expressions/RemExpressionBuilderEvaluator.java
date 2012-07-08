package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.RemExpression;
import com.loki2302.jsick.expressions.Expression;

public class RemExpressionBuilderEvaluator extends Evaluator<TwoExpressionsAndType, Expression> {
	@Override
	public Context<Expression> evaluate(TwoExpressionsAndType input) {		
		return ok(new RemExpression(input.getLeft(), input.getRight(), input.getType())); 
	}		
}