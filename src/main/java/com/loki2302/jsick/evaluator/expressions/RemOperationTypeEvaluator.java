package com.loki2302.jsick.evaluator.expressions;

import static com.loki2302.jsick.evaluator.Fluency.tuple3;
import static com.loki2302.jsick.evaluator.expressions.Fluency.expressionIsOfType;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

public class RemOperationTypeEvaluator extends BinaryOperationTypeEvaluator {
	
	private final Types types;
	
	public RemOperationTypeEvaluator(Types types) {
		this.types = types;
	}
	
	@Override
	protected Evaluator<Tuple2<TypedExpression, TypedExpression>, Tuple3<TypedExpression, TypedExpression, Type>> makeEvaluator() {
		Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> first =
						expressionIsOfType(first(), type(types.IntType));
		
		Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> second =
						expressionIsOfType(second(), type(types.IntType));
		
		return tuple3(first, second, type(types.IntType));
	}		
}