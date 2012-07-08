package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMSubExpression;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressionsAndType;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.expressions.SubExpression;
import com.loki2302.jsick.types.Type;

public class SubExpressionEvaluator extends AbstractArithmeticExpressionEvaluator<DOMSubExpression> {	

	public SubExpressionEvaluator(
			Evaluator<DOMExpression, Expression> genericExpressionEvaluator,
			Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator) {
		super(genericExpressionEvaluator, operationTypeEvaluator);
	}

	@Override
	protected Expression makeExpression(DOMExpression sourceDOMExpression, Expression leftExpression, Expression rightExpression, Type operationType) {
		return new SubExpression(sourceDOMExpression, leftExpression, rightExpression, operationType);
	}	
	
}
