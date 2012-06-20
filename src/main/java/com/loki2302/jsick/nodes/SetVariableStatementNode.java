package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public class SetVariableStatementNode extends StatementNode {
	
	private final String name;
	private final ExpressionNode expression;
	
	public SetVariableStatementNode(String name, ExpressionNode expression) {
		this.name = name;
		this.expression = expression;
	}
	
	public String getName() {
		return name;
	}
	
	public ExpressionNode getExpression() {
		return expression;
	}
	
	@Override
	void execute(ExecutionContext context) {
		System.out.printf("SET %s to %s\n", name, expression.getValue(context));
		context.setVariable(name, expression.getValue(context));
	}
	
	@Override
	public String toString() {
		return String.format("Set{%s}", name);
	}
	
}
