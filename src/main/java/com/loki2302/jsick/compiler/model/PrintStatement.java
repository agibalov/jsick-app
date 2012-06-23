package com.loki2302.jsick.compiler.model;

import com.loki2302.jsick.compiler.model.expressions.Expression;

public class PrintStatement extends Statement {
	
	private final Expression expression;
	
	public PrintStatement(Expression expression) {
		this(expression, null);
	}
		
	public PrintStatement(Expression expression, Object sourceContext) {
		super(sourceContext);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}

}
