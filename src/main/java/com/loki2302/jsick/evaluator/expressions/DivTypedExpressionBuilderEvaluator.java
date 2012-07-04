package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.DivExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class DivTypedExpressionBuilderEvaluator extends Evaluator<Tuple3<TypedExpression, TypedExpression, Type>, TypedExpression> {
	@Override
	public Context<TypedExpression> evaluate(Context<Tuple3<TypedExpression, TypedExpression, Type>> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		Tuple3<TypedExpression, TypedExpression, Type> tuple = input.getValue();			
		return Context.<TypedExpression>ok(
				new DivExpression(
						tuple.first.getValue(),
						tuple.second.getValue(),
						tuple.third.getValue())); 
	}		
}