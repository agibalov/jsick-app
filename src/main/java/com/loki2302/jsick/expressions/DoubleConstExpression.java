package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class DoubleConstExpression implements Expression {
	private final Type doubleType;
	private final double value;
	
	public DoubleConstExpression(Type doubleType, double value) {
		this.doubleType = doubleType;
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public Type getType() {			
		return doubleType;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitDoubleConstExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("c{%f}", value);
	}
}