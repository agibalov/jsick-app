package com.loki2302.jsick.evaluator.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.dom.expressions.DOMBinaryExpression;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.expressions.Expression;

public abstract class AbstractBinaryExpressionEvaluator<TExpression extends DOMBinaryExpression> 
extends Evaluator<TExpression, Expression> {
	
	private final Evaluator<DOMExpression, Expression> genericExpressionEvaluator;
	
	protected AbstractBinaryExpressionEvaluator(
			Evaluator<DOMExpression, Expression> genericExpressionEvaluator) {
		this.genericExpressionEvaluator = genericExpressionEvaluator;
	}

	@Override
	public Context<Expression> evaluate(TExpression input) {
		
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		Context<Expression> leftExpressionContext = genericExpressionEvaluator.evaluate(input.getLeft());
		if(!leftExpressionContext.isOk()) {
			errors.add(leftExpressionContext.getError());
		}
		
		Context<Expression> rightExpressionContext = genericExpressionEvaluator.evaluate(input.getRight());
		if(!rightExpressionContext.isOk()) {
			errors.add(rightExpressionContext.getError());
		}
		
		if(!errors.isEmpty()) {
			return fail(new CompositeError(this, input, errors));
		}
		
		return processExpressions(input, leftExpressionContext.getValue(), rightExpressionContext.getValue());
	}
	
	protected abstract Context<Expression> processExpressions(DOMExpression sourceDOMExpression, Expression leftExpression, Expression rightExpression);	
}
