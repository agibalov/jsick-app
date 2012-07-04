package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class Fluency {
	
	public static <TInput> GetExpressionTypeEvaluator<TInput> typeOf(Evaluator<TInput, TypedExpression> expressionEvaluator) {
		return new GetExpressionTypeEvaluator<TInput>(expressionEvaluator);
	}
	
	public static <TInput> CastExpressionToTypeEvaluator<TInput> castExpressionToType(
			Evaluator<TInput, TypedExpression> sourceExpressionEvaluator, 
			Evaluator<TInput, Type> targetTypeEvaluator) {
		return new CastExpressionToTypeEvaluator<TInput>(sourceExpressionEvaluator, targetTypeEvaluator);
	}
	
	public static <TInput> ExpressionIsOfTypeEvaluator<TInput> expressionIsOfType(
			Evaluator<TInput, TypedExpression> expressionEvaluator, 
			Evaluator<TInput, Type> typeEvaluator) {
		return new ExpressionIsOfTypeEvaluator<TInput>(expressionEvaluator, typeEvaluator);
	}
	
	public static <TInput> FixedTypeEvaluator<TInput> type(Class<TInput> clazz, Type type) {
		return new FixedTypeEvaluator<TInput>(type);
	}
	
}