package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Type;

public class DoubleConstExpression extends Expression {
	private final Type doubleType;
	private final double value;
	
	public DoubleConstExpression(DOMExpression sourceDOMExpression, Type doubleType, double value) {
		super(sourceDOMExpression);
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