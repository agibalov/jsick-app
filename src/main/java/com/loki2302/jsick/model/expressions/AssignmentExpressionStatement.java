package com.loki2302.jsick.model.expressions;

import com.loki2302.jsick.model.ExecutionContext;
import com.loki2302.jsick.model.Statement;

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
