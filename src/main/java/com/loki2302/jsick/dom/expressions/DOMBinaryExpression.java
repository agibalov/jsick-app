package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;

public abstract class DOMBinaryExpression extends DOMExpression {
	
	private final DOMExpression left;
	private final DOMExpression right;
	
	protected DOMBinaryExpression(DOMExpression left, DOMExpression right, IndexRange matchRange) {
		super(matchRange);
		this.left = left;
		this.right = right;
	}
	
	public DOMExpression getLeft() {
		return left;
	}
	
	public DOMExpression getRight() {
		return right;
	}
	
	public static DOMBinaryExpression expressionFromChar(char c, DOMExpression left, DOMExpression right, IndexRange indexRange) {
		if(c == '+') {
			return new DOMAddExpression(left, right, indexRange);
		} else if(c == '-') {
			return new DOMSubExpression(left, right, indexRange);
		} else if(c == '*') {
			return new DOMMulExpression(left, right, indexRange);
		} else if(c == '/') {
			return new DOMDivExpression(left, right, indexRange);
		} else if(c == '%') {
			return new DOMRemExpression(left, right, indexRange);
		}
		
		throw new RuntimeException();
	}

}
