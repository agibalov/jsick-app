package com.loki2302.jsick.model.expressions;

public class VariableReferenceExpression extends Expression {
	
	private final String name;
	
	public VariableReferenceExpression(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
