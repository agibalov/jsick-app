package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMMulExpression;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressionsAndType;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.expressions.MulExpression;
import com.loki2302.jsick.types.Type;

public class MulExpressionEvaluator extends AbstractArithmeticExpressionEvaluator<DOMMulExpression> {	

	public MulExpressionEvaluator(
			Evaluator<DOMExpression, Expression> genericExpressionEvaluator,
			Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator) {
		super(genericExpressionEvaluator, operationTypeEvaluator);
	}

	@Override
	protected Expression makeExpression(DOMExpression sourceDOMExpression, Expression leftExpression, Expression rightExpression, Type operationType) {
		return new MulExpression(sourceDOMExpression, leftExpression, rightExpression, operationType);
	}	
	
}
