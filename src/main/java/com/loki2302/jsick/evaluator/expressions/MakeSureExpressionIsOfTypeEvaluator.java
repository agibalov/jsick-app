package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.expressions.errors.CannotCastImplicitlyError;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class MakeSureExpressionIsOfTypeEvaluator extends Evaluator<Tuple2<Expression, Type>, Expression> {

	@Override
	public Context<Expression> evaluate(Tuple2<Expression, Type> input) {
		Expression expression = input.first;
		Type expressionType = expression.getType();
		Type targetType = input.second;
		
		if(expressionType.equals(targetType)) {
			return ok(expression);
		}
		
		if(expressionType.canImplicitlyCastTo(targetType)) {
			return ok(new CastExpression(expression, targetType));
		}
		
		return fail(new CannotCastImplicitlyError(this, input, expression, targetType));
	}
	
}