package com.loki2302.jsick.evaluator.expressions.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class ExpressionIsNotOfType extends AbstractError {
	
	private final Expression expression;
	private final Type type;
	
	public ExpressionIsNotOfType(Evaluator<?, ?> evaluator, Object input, Expression expression, Type type) {
		super(evaluator, input);
		this.expression = expression;
		this.type = type;
	}		
	
	@Override
	public String toString() {
		return String.format("ExpressionIsNotOfType{%s,%s}", expression.getType(), type);
	}
}