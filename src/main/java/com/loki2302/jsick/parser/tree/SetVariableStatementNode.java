package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class SetVariableStatementNode extends StatementNode {
	
	private final String name;
	private final ExpressionNode expression;
	
	public SetVariableStatementNode(String name, ExpressionNode expression, IndexRange indexRange) {
		super(indexRange);
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
	public String toString() {
		return String.format("Set{%s}", name);
	}
	
}
