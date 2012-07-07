package com.loki2302.jsick.evaluator.expressions.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.expressions.Expression;

public class ExpressionIsNotLvalueError extends AbstractError {
	
	private final Expression expression; 
	
	public ExpressionIsNotLvalueError(Evaluator<?, ?> evaluator, Object input, Expression expression) {
		super(evaluator, input);
		this.expression = expression;
	}		
	
	@Override
	public String toString() {
		return String.format("NotLvalue{%s}", expression.getType());
	}
}