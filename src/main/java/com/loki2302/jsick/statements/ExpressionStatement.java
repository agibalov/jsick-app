package com.loki2302.jsick.statements;

import com.loki2302.jsick.expressions.Expression;

public class ExpressionStatement extends Statement {
	private final Expression expression;
	
	public ExpressionStatement(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitExpressionStatement(this);
	}
}
