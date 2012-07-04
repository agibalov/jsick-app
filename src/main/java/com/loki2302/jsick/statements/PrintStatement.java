package com.loki2302.jsick.statements;

import com.loki2302.jsick.expressions.TypedExpression;

public class PrintStatement extends Statement {
	
	private final TypedExpression expression;
	
	public PrintStatement(TypedExpression expression) {
		this.expression = expression;
	}
	
	public TypedExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitPrintStatement(this);
	}

}
