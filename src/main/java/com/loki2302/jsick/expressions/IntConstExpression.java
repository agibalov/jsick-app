package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.SimpleType;
import com.loki2302.jsick.types.Type;

public class IntConstExpression implements TypedExpression {
	private final int value;
	
	public IntConstExpression(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public Type getType() {			
		return new SimpleType("int", new SimpleType("double"));
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