package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;

public class DOMDivExpression extends DOMBinaryExpression {	
	public DOMDivExpression(DOMExpression left, DOMExpression right, IndexRange matchRange) {
		super(left, right, matchRange);
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMDivExpression(this);
	}
}
