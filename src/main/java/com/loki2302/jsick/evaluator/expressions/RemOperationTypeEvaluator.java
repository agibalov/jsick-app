package com.loki2302.jsick.evaluator.expressions;

import static com.loki2302.jsick.evaluator.Fluency.tuple3;
import static com.loki2302.jsick.evaluator.expressions.Fluency.expressionIsOfType;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

public class RemOperationTypeEvaluator extends BinaryOperationTypeEvaluator<Expression, Expression> {
	
	private final Types types;
	
	public RemOperationTypeEvaluator(Types types) {
		this.types = types;
	}
	
	@Override
	protected Evaluator<Tuple2<Expression, Expression>, Tuple3<Expression, Expression, Type>> makeEvaluator() {
		Evaluator<Tuple2<Expression, Expression>, Expression> first =
						expressionIsOfType(first(), type(types.IntType));
		
		Evaluator<Tuple2<Expression, Expression>, Expression> second =
						expressionIsOfType(second(), type(types.IntType));
		
		return tuple3(first, second, type(types.IntType));
	}		
}