package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.MulExpression;
import com.loki2302.jsick.expressions.Expression;

public class MulExpressionBuilderEvaluator extends Evaluator<TwoExpressionsAndType, Expression> {
	@Override
	public Context<Expression> evaluate(TwoExpressionsAndType input) {				
		return ok(
				new MulExpression(
						input.getLeft(),
						input.getRight(),
						input.getType())); 
	}		
}