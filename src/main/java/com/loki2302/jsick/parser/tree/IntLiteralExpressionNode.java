package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class IntLiteralExpressionNode extends LiteralExpressionNode {
	
	public IntLiteralExpressionNode(String value, IndexRange indexRange) {
		super(value, indexRange);
	}

}
