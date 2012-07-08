package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Type;

public class IntConstExpression extends Expression {
	private final Type intType;
	private final int value;
	
	public IntConstExpression(DOMExpression sourceDOMExpression, Type intType, int value) {
		super(sourceDOMExpression);
		this.intType = intType;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public Type getType() {			
		return intType;
	}    	
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitIntConstExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("c{%d}", value);
	}
}