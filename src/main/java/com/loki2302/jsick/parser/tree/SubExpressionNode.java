package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class SubExpressionNode extends AbstractBinaryExpressionNode {
	public SubExpressionNode(ExpressionNode a, ExpressionNode b, IndexRange indexRange) {
		super(a, b, indexRange);
	}		
}