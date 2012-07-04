package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.SimpleType;
import com.loki2302.jsick.types.Type;

public class DoubleConstExpression implements TypedExpression {
	private final double value;
	
	public DoubleConstExpression(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public Type getType() {			
		return new SimpleType("double");
	}
	
	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitDoubleConstExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("c{%f}", value);
	}
}