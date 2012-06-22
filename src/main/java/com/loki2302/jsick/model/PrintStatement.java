package com.loki2302.jsick.model;

import com.loki2302.jsick.model.expressions.Expression;

public class PrintStatement extends Statement {
	
	private final Expression expression;
	
	public PrintStatement(Expression expression) {
		this.expression = expression;
	}

	@Override
	public void execute(ExecutionContext context) {
		System.out.printf("PRINT %d\n", expression.getValue(context));
	}

}
