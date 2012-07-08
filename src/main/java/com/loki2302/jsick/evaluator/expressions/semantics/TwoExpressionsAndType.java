package com.loki2302.jsick.evaluator.expressions.semantics;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class TwoExpressionsAndType {
	private final Expression left;
	private final Expression right;
	private final Type type;
	
	public TwoExpressionsAndType(Expression left, Expression right, Type type) {
		this.left = left;
		this.right = right;
		this.type = type;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
	
	public Type getType() {
		return type;
	}
	
	public static class MakeTwoExpressionsAndTypeEvaluator<TInput> extends Evaluator<TInput, TwoExpressionsAndType> {
		private final Evaluator<TInput, ? extends Expression> leftExpressionEvaluator;
		private final Evaluator<TInput, Expression> rightExpressionEvaluator;
		private final Evaluator<TInput, Type> typeEvaluator;
		
		public MakeTwoExpressionsAndTypeEvaluator(
				Evaluator<TInput, ? extends Expression> leftExpressionEvaluator,
				Evaluator<TInput, Expression> rightExpressionEvaluator,
				Evaluator<TInput, Type> typeEvaluator) {
			this.leftExpressionEvaluator = leftExpressionEvaluator;
			this.rightExpressionEvaluator = rightExpressionEvaluator;
			this.typeEvaluator = typeEvaluator;
		}
		
		@Override
		public Context<TwoExpressionsAndType> evaluate(TInput input) {
			List<AbstractError> errors = new ArrayList<AbstractError>();
			
			Context<? extends Expression> leftExpressionContext = leftExpressionEvaluator.evaluate(input);
			if(!leftExpressionContext.isOk()) {
				errors.add(leftExpressionContext.getError());
			}
			
			Context<Expression> rightExpressionContext = rightExpressionEvaluator.evaluate(input);
			if(!rightExpressionContext.isOk()) {
				errors.add(rightExpressionContext.getError());
			}
			
			Context<Type> typeContext = typeEvaluator.evaluate(input);
			if(!typeContext.isOk()) {
				errors.add(typeContext.getError());
			}
			
			if(!errors.isEmpty()) {
				return fail(new CompositeError(this, input, errors));
			}			

			return ok(new TwoExpressionsAndType(
					leftExpressionContext.getValue(), 
					rightExpressionContext.getValue(), 
					typeContext.getValue()));
		}		
	}
}