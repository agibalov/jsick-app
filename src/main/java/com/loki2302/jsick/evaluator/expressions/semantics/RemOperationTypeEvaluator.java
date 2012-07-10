package com.loki2302.jsick.evaluator.expressions.semantics;

import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.expressionIsOfType;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Types;

public class RemOperationTypeEvaluator extends BinaryOperationTypeEvaluator {
	
	private final Types types;
	
	public RemOperationTypeEvaluator(Types types) {
		this.types = types;
	}
	
	@Override
	protected Evaluator<TwoExpressions, TwoExpressionsAndType> makeEvaluator() {
		Evaluator<TwoExpressions, Expression> first =
						expressionIsOfType(first(), type(types.IntType));
		
		Evaluator<TwoExpressions, Expression> second =
						expressionIsOfType(second(), type(types.IntType));
		
		return twoExpressionsAndType(first, second, type(types.IntType));
	}		
}