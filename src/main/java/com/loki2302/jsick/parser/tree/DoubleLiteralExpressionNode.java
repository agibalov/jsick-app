package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class DoubleLiteralExpressionNode extends LiteralExpressionNode {
	
	public DoubleLiteralExpressionNode(String value, IndexRange indexRange) {
		super(value, indexRange);
	}

}
