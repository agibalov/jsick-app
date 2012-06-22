package com.loki2302.jsick.parser.tree;

public class LiteralExpressionNode extends ExpressionNode {
	
	private final String value;
	
	public LiteralExpressionNode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
