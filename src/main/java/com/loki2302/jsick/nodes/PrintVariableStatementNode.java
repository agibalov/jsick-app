package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public class PrintVariableStatementNode extends StatementNode {
	
	private final ExpressionNode expression;
	
	public PrintVariableStatementNode(ExpressionNode expression) {
		this.expression = expression;
	}
	
	public ExpressionNode getExpression() {
		return expression;
	}
	
	@Override
	void execute(ExecutionContext context) {
		System.out.printf("PRINT %d\n", expression.getValue(context));		
	}
	
	@Override
	public String toString() {
		return String.format("Print");
	}

}
