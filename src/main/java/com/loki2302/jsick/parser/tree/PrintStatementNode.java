package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class PrintStatementNode extends StatementNode {
	
	private final ExpressionNode expression;
	
	public PrintStatementNode(ExpressionNode expression, IndexRange indexRange) {
		super(indexRange);
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
