package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.dom.DOMNode;

public abstract class DOMExpression extends DOMNode {
	
	protected DOMExpression(IndexRange indexRange) {
		super(indexRange);
	}
		
	public abstract <T> T accept(DOMExpressionVisitor<T> visitor);	
}