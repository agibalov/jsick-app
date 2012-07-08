package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMBinaryExpression;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressionsAndType;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public abstract class AbstractArithmeticExpressionEvaluator<TExpression extends DOMBinaryExpression> 
extends AbstractBinaryExpressionEvaluator<TExpression> {	

	private final Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator;
	
	protected AbstractArithmeticExpressionEvaluator(
			Evaluator<DOMExpression, Expression> genericExpressionEvaluator,
			Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator) {
		super(genericExpressionEvaluator);
		this.operationTypeEvaluator = operationTypeEvaluator;
	}
	
	@Override
	protected Context<Expression> processExpressions(Expression leftExpression, Expression rightExpression) {
		Context<TwoExpressionsAndType> typingContext = operationTypeEvaluator.evaluate(
				new TwoExpressions(leftExpression, rightExpression));
		if(!typingContext.isOk()) {
			return fail(typingContext.getError());
		}
		
		TwoExpressionsAndType type = typingContext.getValue();
		
		return ok(makeExpression(type.getLeft(), type.getRight(), type.getType()));
	}
	
	protected abstract Expression makeExpression(Expression leftExpression, Expression rightExpression, Type operationType);	
}
