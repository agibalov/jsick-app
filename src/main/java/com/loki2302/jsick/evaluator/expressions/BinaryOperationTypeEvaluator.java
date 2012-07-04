package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.evaluator.GetFirstEvaluator;
import com.loki2302.jsick.evaluator.GetSecondEvaluator;
import com.loki2302.jsick.evaluator.LazyEvaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public abstract class BinaryOperationTypeEvaluator 
extends LazyEvaluator<Tuple2<TypedExpression, TypedExpression>, Tuple3<TypedExpression, TypedExpression, Type>> {
	protected GetFirstEvaluator<TypedExpression, Tuple2<TypedExpression, TypedExpression>> first() {
		return new GetFirstEvaluator<TypedExpression, Tuple2<TypedExpression, TypedExpression>>();
	}    	
	
	protected GetSecondEvaluator<TypedExpression, Tuple2<TypedExpression, TypedExpression>> second() {
		return new GetSecondEvaluator<TypedExpression, Tuple2<TypedExpression, TypedExpression>>();
	}
	
	protected FixedTypeEvaluator<Tuple2<TypedExpression, TypedExpression>> type(Type type) {
		return new FixedTypeEvaluator<Tuple2<TypedExpression, TypedExpression>>(type);
	}
}