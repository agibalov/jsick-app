package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;


public class DOMIntConstExpression extends DOMExpression {   
	private final int value;
	
	public DOMIntConstExpression(int value, IndexRange indexRange) {
		super(indexRange);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMIntConstExpression(this);
	}
}