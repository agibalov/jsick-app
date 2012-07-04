package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class DivExpressionNode extends AbstractBinaryExpressionNode {
	public DivExpressionNode(ExpressionNode a, ExpressionNode b, IndexRange indexRange) {
		super(a, b, indexRange);
	}		
}