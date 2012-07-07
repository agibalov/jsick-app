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
import com.loki2302.jsick.types.Types;

public class AddSubMulDivOperationTypeEvaluator extends BinaryOperationTypeEvaluator<Expression, Expression> {
	
	private final Types types;
	
	public AddSubMulDivOperationTypeEvaluator(Types types) {
		this.types = types;
	}
	
	@Override
	protected Evaluator<Tuple2<Expression, Expression>, Tuple3<Expression, Expression, Type>> makeEvaluator() {
		Evaluator<Tuple2<Expression, Expression>, Expression> first =
				or(
						expressionIsOfType(first(), type(types.IntType)),
						expressionIsOfType(first(), type(types.DoubleType)));
		
		Evaluator<Tuple2<Expression, Expression>, Expression> second =
				or(
						expressionIsOfType(second(), type(types.IntType)),
						expressionIsOfType(second(), type(types.DoubleType)));
		
		Evaluator<Tuple2<Expression, Expression>, Tuple3<Expression, Expression, Type>> expressionsOfSameType = 
				tuple3(
						expressionIsOfType(first, typeOf(second)),
						expressionIsOfType(second, typeOf(first)),
						typeOf(first));
		
		Evaluator<Tuple2<Expression, Expression>, Tuple3<Expression, Expression, Type>> castRightToLeft =
				tuple3(
						first,
						castExpressionToType(second, typeOf(first)),
						typeOf(first));
		
		Evaluator<Tuple2<Expression, Expression>, Tuple3<Expression, Expression, Type>> castLeftToRight =
				tuple3(	
						castExpressionToType(first, typeOf(second)),
		    			second,
		    			typeOf(second));
		
		Evaluator<Tuple2<Expression, Expression>, Tuple3<Expression, Expression, Type>> result = or(
				expressionsOfSameType,
				castRightToLeft,
				castLeftToRight);
		
		return result;
	}		
}