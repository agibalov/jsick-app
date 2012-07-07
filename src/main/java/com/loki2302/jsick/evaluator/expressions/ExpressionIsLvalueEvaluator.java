package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.errors.ExpressionIsNotLvalueError;
import com.loki2302.jsick.expressions.LvalueExpression;
import com.loki2302.jsick.expressions.Expression;

public class ExpressionIsLvalueEvaluator<TInput> extends Evaluator<TInput, LvalueExpression> {
	
	private final Evaluator<TInput, Expression> evaluator;
	
	public ExpressionIsLvalueEvaluator(Evaluator<TInput, Expression> evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public Context<LvalueExpression> evaluate(TInput input) {
		Context<Expression> expressionContext = evaluator.evaluate(input);
		if(!expressionContext.isOk()) {
			return fail(expressionContext.getError());
		}
		
		Expression expression = expressionContext.getValue();
		if(!(expression instanceof LvalueExpression)) {
			return fail(new ExpressionIsNotLvalueError(this, input));
		}
		
		return ok((LvalueExpression)expression);
	}
	
}