package com.loki2302.jsick.evaluator.expressions.semantics;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.LazyEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions.GetLeftExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions.GetRightExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressionsAndType.MakeTwoExpressionsAndTypeEvaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public abstract class BinaryOperationTypeEvaluator 
extends LazyEvaluator<TwoExpressions, TwoExpressionsAndType> {
	protected GetLeftExpressionEvaluator<TwoExpressions> first() {
		return new GetLeftExpressionEvaluator<TwoExpressions>();
	}    	
	
	protected GetRightExpressionEvaluator<TwoExpressions> second() {
		return new GetRightExpressionEvaluator<TwoExpressions>();
	}
	
	protected FixedTypeEvaluator<TwoExpressions> type(Type type) {
		return new FixedTypeEvaluator<TwoExpressions>(type);
	}	
	
	protected static <TInput> MakeTwoExpressionsAndTypeEvaluator<TInput> twoExpressionsAndType(
			Evaluator<TInput, ? extends Expression> leftExpressionEvaluator,
			Evaluator<TInput, Expression> rightExpressionEvaluator,
			Evaluator<TInput, Type> typeEvaluator) {
		return new MakeTwoExpressionsAndTypeEvaluator<TInput>(leftExpressionEvaluator, rightExpressionEvaluator, typeEvaluator);
	}
}