package com.loki2302.jsick.compiler.model.expressions;

public abstract class Expression {

	private final Object sourceContext;
	
	protected Expression(Object sourceContext) {
		this.sourceContext = sourceContext;
	}
	
	public Object getSourceContext() {
		return sourceContext;
	}
	
}
