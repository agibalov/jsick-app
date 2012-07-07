package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.expressions.errors.CannotCastError;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class MakeSureExpressionIsOfTypeEvaluator extends Evaluator<Tuple2<TypedExpression, Type>, TypedExpression> {

	@Override
	public Context<TypedExpression> evaluate(Tuple2<TypedExpression, Type> input) {
		TypedExpression expression = input.first.getValue();
		Type expressionType = expression.getType();
		Type type = input.second.getValue();
		
		if(expressionType.equals(type)) {
			return ok(expression);
		}
		
		if(expressionType.canImplicitlyCastTo(type)) {
			return ok(new CastExpression(expression, type));
		}
		
		return fail(new CannotCastError(this, input));
	}
	
}