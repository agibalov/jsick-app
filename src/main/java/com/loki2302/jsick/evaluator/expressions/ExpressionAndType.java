package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class ExpressionAndType {
	private final Expression expression;
	private final Type type;
	
	public ExpressionAndType(Expression expression, Type type) {
		this.expression = expression;
		this.type = type;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Type getType() {
		return type;
	}
}