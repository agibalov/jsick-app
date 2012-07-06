package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.evaluator.expressions.errors.CannotDeduceOperationTypeError;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class BinaryOperationEvaluator 
extends Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> {
	
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, Tuple3<TypedExpression, TypedExpression, Type>> myOperationTypeEvaluator;
	private final Evaluator<Tuple3<TypedExpression, TypedExpression, Type>, TypedExpression> typedExpressionBuilderEvaluator;
	
	public BinaryOperationEvaluator(
			Evaluator<Tuple2<TypedExpression, TypedExpression>, Tuple3<TypedExpression, TypedExpression, Type>> operationTypeEvaluator,
			Evaluator<Tuple3<TypedExpression, TypedExpression, Type>, TypedExpression> typedExpressionBuilderEvaluator) {
		this.myOperationTypeEvaluator = operationTypeEvaluator;
		this.typedExpressionBuilderEvaluator = typedExpressionBuilderEvaluator;
	}

	@Override
	public Context<TypedExpression> evaluate(Tuple2<TypedExpression, TypedExpression> input) {		
		Context<Tuple3<TypedExpression, TypedExpression, Type>> operationTypeContext = myOperationTypeEvaluator.evaluate(input);
		if(!operationTypeContext.isOk()) {
			return fail(new CannotDeduceOperationTypeError(this, input));
		}
		
		return typedExpressionBuilderEvaluator.evaluate(operationTypeContext);
	}
}