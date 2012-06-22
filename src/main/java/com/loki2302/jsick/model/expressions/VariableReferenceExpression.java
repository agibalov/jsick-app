package com.loki2302.jsick.model.expressions;

import com.loki2302.jsick.model.ExecutionContext;

public class VariableReferenceExpression extends Expression {
	
	private final String name;
	
	public VariableReferenceExpression(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int getValue(ExecutionContext context) {
		return context.getVariableValue(name);
	}

}
