package com.loki2302.jsick.model.expressions;

public class LiteralExpression extends Expression {
	
	private final int value;
	
	public LiteralExpression(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
