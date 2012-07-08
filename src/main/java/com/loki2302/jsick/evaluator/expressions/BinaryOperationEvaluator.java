package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.errors.CannotDeduceOperationTypeError;
import com.loki2302.jsick.expressions.Expression;

public class BinaryOperationEvaluator
extends Evaluator<TwoExpressions, Expression> {
	
	private final Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator;
	private final Evaluator<TwoExpressionsAndType, Expression> typedExpressionBuilderEvaluator;
	
	public BinaryOperationEvaluator(
			Evaluator<TwoExpressions, TwoExpressionsAndType> operationTypeEvaluator,
			Evaluator<TwoExpressionsAndType, Expression> typedExpressionBuilderEvaluator) {
		this.operationTypeEvaluator = operationTypeEvaluator;
		this.typedExpressionBuilderEvaluator = typedExpressionBuilderEvaluator;
	}

	@Override
	public Context<Expression> evaluate(TwoExpressions input) {		
		Context<TwoExpressionsAndType> operationTypeContext = operationTypeEvaluator.evaluate(input);
		if(!operationTypeContext.isOk()) {
			return fail(new CannotDeduceOperationTypeError(this, input, input.getLeft(), input.getRight()));
		}
		
		return typedExpressionBuilderEvaluator.evaluate(operationTypeContext);
	}
}