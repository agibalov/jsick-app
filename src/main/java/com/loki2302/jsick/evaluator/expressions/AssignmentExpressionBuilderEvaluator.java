package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.LvalueExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class AssignmentExpressionBuilderEvaluator 
extends Evaluator<Tuple3<LvalueExpression, Expression, Type>, Expression> {
	@Override
	public Context<Expression> evaluate(Tuple3<LvalueExpression, Expression, Type> input) {		
		return ok(input.first.getValue().asSetter(input.second.getValue())); 
	}		
}