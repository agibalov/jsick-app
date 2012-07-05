package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;

public class DOMDoubleConstExpression extends DOMExpression {
	private final double value;
	
	public DOMDoubleConstExpression(double value, IndexRange matchRange) {
		super(matchRange);
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMDoubleConstExpression(this);
	}	
	
}