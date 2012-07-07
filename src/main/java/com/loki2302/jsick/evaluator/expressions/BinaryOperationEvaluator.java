package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.evaluator.expressions.errors.CannotDeduceOperationTypeError;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class BinaryOperationEvaluator<T1, T2>
extends Evaluator<Tuple2<Expression, Expression>, Expression> {
	
	private final Evaluator<Tuple2<Expression, Expression>, Tuple3<T1, T2, Type>> operationTypeEvaluator;
	private final Evaluator<Tuple3<T1, T2, Type>, Expression> typedExpressionBuilderEvaluator;
	
	public BinaryOperationEvaluator(
			Evaluator<Tuple2<Expression, Expression>, Tuple3<T1, T2, Type>> operationTypeEvaluator,
			Evaluator<Tuple3<T1, T2, Type>, Expression> typedExpressionBuilderEvaluator) {
		this.operationTypeEvaluator = operationTypeEvaluator;
		this.typedExpressionBuilderEvaluator = typedExpressionBuilderEvaluator;
	}

	@Override
	public Context<Expression> evaluate(Tuple2<Expression, Expression> input) {		
		Context<Tuple3<T1, T2, Type>> operationTypeContext = operationTypeEvaluator.evaluate(input);
		if(!operationTypeContext.isOk()) {
			return fail(new CannotDeduceOperationTypeError(this, input));
		}
		
		return typedExpressionBuilderEvaluator.evaluate(operationTypeContext);
	}
}