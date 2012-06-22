package com.loki2302.jsick.model;

import com.loki2302.jsick.model.expressions.Expression;

public class AssignmentExpressionStatement extends Statement {
	
	private final String name;
	private final Expression expression;
	
	public AssignmentExpressionStatement(String name, Expression expression) {
		this.name = name;
		this.expression = expression;
	}	
	
	public String getName() {
		return name;
	}
	
	public Expression getExpression() {
		return expression;
	}

}
