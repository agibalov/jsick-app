package com.loki2302.jsick.parser.tree;

public abstract class LiteralExpressionNode extends ExpressionNode {

	protected final String value;

	public LiteralExpressionNode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}