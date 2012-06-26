package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class VariableDefinitionStatementNode extends StatementNode {
	
	private final TypeNode type;
	private final String name;
	private final ExpressionNode expression;
	
	public VariableDefinitionStatementNode(TypeNode type, String name, ExpressionNode expression, IndexRange indexRange) {
		super(indexRange);
		this.type = type;
		this.name = name;
		this.expression = expression;
	}
	
	public TypeNode getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public ExpressionNode getExpression() {
		return expression;
	}
		
	@Override
	public String toString() {
		return String.format("Define{%s}", name);
	}
	
}
