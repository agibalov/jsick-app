package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.SubExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class SubExpressionBuilderEvaluator extends Evaluator<Tuple3<Expression, Expression, Type>, Expression> {
	@Override
	public Context<Expression> evaluate(Tuple3<Expression, Expression, Type> input) {		
		return ok(
				new SubExpression(
						input.first,
						input.second,
						input.third)); 
	}		
}