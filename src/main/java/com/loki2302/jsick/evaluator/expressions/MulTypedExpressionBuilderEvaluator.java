package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.MulExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class MulTypedExpressionBuilderEvaluator extends Evaluator<Tuple3<TypedExpression, TypedExpression, Type>, TypedExpression> {
	@Override
	protected Context<TypedExpression> evaluateImpl(Context<Tuple3<TypedExpression, TypedExpression, Type>> input) {		
		Tuple3<TypedExpression, TypedExpression, Type> tuple = input.getValue();			
		return Context.<TypedExpression>ok(
				new MulExpression(
						tuple.first.getValue(),
						tuple.second.getValue(),
						tuple.third.getValue())); 
	}		
}