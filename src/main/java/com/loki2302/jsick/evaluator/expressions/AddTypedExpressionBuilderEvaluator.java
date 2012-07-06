package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.AddExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class AddTypedExpressionBuilderEvaluator extends Evaluator<Tuple3<TypedExpression, TypedExpression, Type>, TypedExpression> {
	@Override
	public Context<TypedExpression> evaluate(Tuple3<TypedExpression, TypedExpression, Type> input) {		
		return ok(
				new AddExpression(
						input.first.getValue(),
						input.second.getValue(),
						input.third.getValue())); 
	}		
}