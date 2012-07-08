package com.loki2302.jsick.evaluator.expressions.semantics;

import static com.loki2302.jsick.evaluator.Fluency.or;
import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.castExpressionToType;
import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.expressionIsOfType;
import static com.loki2302.jsick.evaluator.expressions.semantics.Fluency.typeOf;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.LvalueExpression;
import com.loki2302.jsick.expressions.Expression;

public class AssignmentSemanticsEvaluator extends BinaryOperationTypeEvaluator {
				
	@Override
	protected Evaluator<TwoExpressions, TwoExpressionsAndType> makeEvaluator() {
		Evaluator<TwoExpressions, LvalueExpression> lvalue =
				expressionIsLvalue(first());
		
		Evaluator<TwoExpressions, Expression> rvalue =
				or(
						expressionIsOfType(second(), typeOf(lvalue)),
						castExpressionToType(second(), typeOf(lvalue)));
		
		Evaluator<TwoExpressions, TwoExpressionsAndType> result =
				twoExpressionsAndType(
						lvalue,
						rvalue,
						typeOf(lvalue));
		
		return result;
	}		
	
	public static <TInput> Evaluator<TInput, LvalueExpression> expressionIsLvalue(
			Evaluator<TInput, Expression> expressionEvaluator) {
		return new ExpressionIsLvalueEvaluator<TInput>(expressionEvaluator);
	}

}