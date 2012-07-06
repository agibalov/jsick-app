package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class IntConstExpression implements TypedExpression {
	private final Type intType;
	private final int value;
	
	public IntConstExpression(Type intType, int value) {
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
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitIntConstExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("c{%d}", value);
	}
}