package com.loki2302.jsick.model.expressions;

import com.loki2302.jsick.model.ExecutionContext;

public class LiteralExpression extends Expression {
	
	private final int value;
	
	public LiteralExpression(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public int getValue(ExecutionContext context) {
		return value;
	}

}
