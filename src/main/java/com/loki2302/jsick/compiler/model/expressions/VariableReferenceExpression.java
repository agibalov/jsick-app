package com.loki2302.jsick.compiler.model.expressions;

public class VariableReferenceExpression extends Expression {
	
	private final String name;
	
	public VariableReferenceExpression(String name) {
		this(name, null);
	}
	
	public VariableReferenceExpression(String name, Object sourceContext) {
		super(sourceContext);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
		
}
