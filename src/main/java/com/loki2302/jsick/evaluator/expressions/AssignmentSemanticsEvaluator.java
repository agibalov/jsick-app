package com.loki2302.jsick.evaluator.expressions;

import static com.loki2302.jsick.evaluator.Fluency.or;
import static com.loki2302.jsick.evaluator.Fluency.tuple3;
import static com.loki2302.jsick.evaluator.expressions.Fluency.castExpressionToType;
import static com.loki2302.jsick.evaluator.expressions.Fluency.expressionIsOfType;
import static com.loki2302.jsick.evaluator.expressions.Fluency.typeOf;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.Tuple3;
import com.loki2302.jsick.expressions.LvalueExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class AssignmentSemanticsEvaluator extends BinaryOperationTypeEvaluator<LvalueExpression, Expression> {
				
	@Override
	protected Evaluator<Tuple2<Expression, Expression>, Tuple3<LvalueExpression, Expression, Type>> makeEvaluator() {
		Evaluator<Tuple2<Expression, Expression>, LvalueExpression> lvalue =
				expressionIsLvalue(first());
		
		Evaluator<Tuple2<Expression, Expression>, Expression> rvalue =
				or(
						expressionIsOfType(second(), typeOf(lvalue)),
						castExpressionToType(second(), typeOf(lvalue)));
		
		Evaluator<Tuple2<Expression, Expression>, Tuple3<LvalueExpression, Expression, Type>> result = 
				tuple3(
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