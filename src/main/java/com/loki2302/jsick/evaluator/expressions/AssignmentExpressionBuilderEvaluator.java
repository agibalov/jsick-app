package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.expressions.LvalueExpression;

public class AssignmentExpressionBuilderEvaluator 
extends Evaluator<TwoExpressionsAndType, Expression> {
	@Override
	public Context<Expression> evaluate(TwoExpressionsAndType input) {		
		return ok(((LvalueExpression)input.getLeft()).asSetter(input.getRight())); 
	}		
}