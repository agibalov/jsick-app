package com.loki2302.jsick.evaluator.expressions.semantics;

import static com.loki2302.jsick.evaluator.Fluency.or;
import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.castExpressionToType;
import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.expressionIsOfType;
import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.typeOf;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Types;

public class ArithmeticOperationTypeEvaluator extends BinaryOperationTypeEvaluator {
	
	private final Types types;
	
	public ArithmeticOperationTypeEvaluator(Types types) {
		this.types = types;
	}
	
	@Override
	protected Evaluator<TwoExpressions, TwoExpressionsAndType> makeEvaluator() {
		Evaluator<TwoExpressions, Expression> first =
				or(
						expressionIsOfType(first(), type(types.IntType)),
						expressionIsOfType(first(), type(types.DoubleType)));
		
		Evaluator<TwoExpressions, Expression> second =
				or(
						expressionIsOfType(second(), type(types.IntType)),
						expressionIsOfType(second(), type(types.DoubleType)));
		
		Evaluator<TwoExpressions, TwoExpressionsAndType> expressionsOfSameType = 
				twoExpressionsAndType(
						expressionIsOfType(first, typeOf(second)),
						expressionIsOfType(second, typeOf(first)),
						typeOf(first));
		
		Evaluator<TwoExpressions, TwoExpressionsAndType> castRightToLeft =
				twoExpressionsAndType(
						first,
						castExpressionToType(second, typeOf(first)),
						typeOf(first));
		
		Evaluator<TwoExpressions, TwoExpressionsAndType> castLeftToRight =
				twoExpressionsAndType(	
						castExpressionToType(first, typeOf(second)),
		    			second,
		    			typeOf(second));
		
		Evaluator<TwoExpressions, TwoExpressionsAndType> result = or(
				expressionsOfSameType,
				castRightToLeft,
				castLeftToRight);
		
		return result;
	}	
}