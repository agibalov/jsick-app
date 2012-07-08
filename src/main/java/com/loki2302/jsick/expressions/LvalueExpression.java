package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;

public abstract class LvalueExpression extends Expression {
	
	protected LvalueExpression(DOMExpression sourceDOMExpression) {
		super(sourceDOMExpression);
	}

	public abstract Expression asSetter(DOMExpression sourceDOMExpression, Expression expression);
	
}