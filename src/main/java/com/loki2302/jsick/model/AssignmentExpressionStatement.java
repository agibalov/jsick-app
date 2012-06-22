package com.loki2302.jsick.model;

import com.loki2302.jsick.model.expressions.Expression;

public class AssignmentExpressionStatement extends Statement {
	
	private final String name;
	private final Expression expression;
	
	public AssignmentExpressionStatement(String name, Expression expression) {
		this.name = name;
		this.expression = expression;
	}	

	@Override
	public void execute(ExecutionContext context) {
		int value = expression.getValue(context);
		System.out.printf("SET %s = %d\n", name, value);
		context.setVariable(name, value);		
	}

}
