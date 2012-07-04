package com.loki2302.jsick.statements;

import com.loki2302.jsick.expressions.TypedExpression;

public class ExpressionStatement extends Statement {
	private final TypedExpression expression;
	
	public ExpressionStatement(TypedExpression expression) {
		this.expression = expression;
	}
	
	public TypedExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitExpressionStatement(this);
	}
}
