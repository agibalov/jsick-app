package com.loki2302.jsick.evaluator.expressions.semantics;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;

public class TwoExpressions {
	private final Expression left;
	private final Expression right;
	
	public TwoExpressions(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
	
	public static class GetLeftExpressionEvaluator<TInput extends TwoExpressions> extends Evaluator<TInput, Expression> {
		@Override
		public Context<Expression> evaluate(TInput input) {
			return ok(input.getLeft());
		}		
	}
	
	public static class GetRightExpressionEvaluator<TInput extends TwoExpressions> extends Evaluator<TInput, Expression> {
		@Override
		public Context<Expression> evaluate(TInput input) {
			return ok(input.getRight());
		}		
	}
}