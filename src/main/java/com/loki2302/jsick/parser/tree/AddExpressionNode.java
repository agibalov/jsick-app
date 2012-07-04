package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class AddExpressionNode extends AbstractBinaryExpressionNode {
	public AddExpressionNode(ExpressionNode a, ExpressionNode b, IndexRange indexRange) {
		super(a, b, indexRange);
	}		
}