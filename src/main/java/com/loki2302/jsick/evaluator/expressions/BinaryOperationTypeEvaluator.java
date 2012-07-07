package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.GetFirstEvaluator;
import com.loki2302.jsick.evaluator.GetSecondEvaluator;
import com.loki2302.jsick.evaluator.LazyEvaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public abstract class BinaryOperationTypeEvaluator<TLeft, TRight> 
extends LazyEvaluator<Tuple2<Expression, Expression>, Tuple3<TLeft, TRight, Type>> {
	protected GetFirstEvaluator<Expression, Tuple2<Expression, Expression>> first() {
		return new GetFirstEvaluator<Expression, Tuple2<Expression, Expression>>();
	}    	
	
	protected GetSecondEvaluator<Expression, Tuple2<Expression, Expression>> second() {
		return new GetSecondEvaluator<Expression, Tuple2<Expression, Expression>>();
	}
	
	protected FixedTypeEvaluator<Tuple2<Expression, Expression>> type(Type type) {
		return new FixedTypeEvaluator<Tuple2<Expression, Expression>>(type);
	}
}