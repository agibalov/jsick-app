package com.loki2302.jsick.model;

import com.loki2302.jsick.model.expressions.Expression;

public class PrintStatement extends Statement {
	
	private final Expression expression;
	
	public PrintStatement(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}

}
