package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public abstract class ExpressionNode extends Node {

	protected ExpressionNode(IndexRange indexRange) {
		super(indexRange);
	}
}
