package com.loki2302.jsick.compiler.expressiondetails;

import com.loki2302.jsick.types.JType;

public abstract class ExpressionCompilationDetails {
	private final JType type;
	
	public ExpressionCompilationDetails(JType type) {
		this.type = type;
	}
	
	public JType getType() {
		return type;
	}
	
	public abstract boolean hasErrors();
}