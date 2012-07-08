package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMRemExpression;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressionsAndType;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.expressions.RemExpression;
import com.loki2302.jsick.types.Type;

public class RemExpressionEvaluator extends AbstractArithmeticExpressionEvaluator<DOMRemExpression> {	

	public RemExpressionEvaluator(
			Evaluator<DOMExpression, Expression> genericExpressionEvaluator,
			Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator) {
		super(genericExpressionEvaluator, operationTypeEvaluator);
	}

	@Override
	protected Expression makeExpression(Expression leftExpression, Expression rightExpression, Type operationType) {
		return new RemExpression(leftExpression, rightExpression, operationType);
	}	
	
}
