package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class RemExpressionNode extends AbstractBinaryExpressionNode {
	public RemExpressionNode(ExpressionNode a, ExpressionNode b, IndexRange indexRange) {
		super(a, b, indexRange);
	}		
}