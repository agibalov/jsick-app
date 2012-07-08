package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Type;

public abstract class Expression {
	
	private final DOMExpression sourceDOMExpression;
	
	protected Expression(DOMExpression sourceDOMExpression) {
		this.sourceDOMExpression = sourceDOMExpression;
	}
	
	public DOMExpression getSourceDOMExpression() {
		return sourceDOMExpression;
	}
	
	public abstract Type getType();
	public abstract <T> T accept(ExpressionVisitor<T> visitor);
}