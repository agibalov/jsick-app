package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class MulExpressionNode extends AbstractBinaryExpressionNode {
	public MulExpressionNode(ExpressionNode a, ExpressionNode b, IndexRange indexRange) {
		super(a, b, indexRange);
	}		
}