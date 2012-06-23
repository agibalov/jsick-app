package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public abstract class LiteralExpressionNode extends ExpressionNode {

	protected final String value;

	public LiteralExpressionNode(String value, IndexRange indexRange) {
		super(indexRange);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}