package com.loki2302.jsick.parser.tree;

public class PrintStatementNode extends StatementNode {
	
	private final ExpressionNode expression;
	
	public PrintStatementNode(ExpressionNode expression) {
		this.expression = expression;
	}
	
	public ExpressionNode getExpression() {
		return expression;
	}
		
	@Override
	public String toString() {
		return String.format("Print");
	}

}
