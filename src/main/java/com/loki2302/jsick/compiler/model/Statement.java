package com.loki2302.jsick.compiler.model;

public abstract class Statement {
	
	private final Object sourceContext;
	
	protected Statement(Object sourceContext) {
		this.sourceContext = sourceContext;
	}
	
	public Object getSourceContext() {
		return sourceContext;
	}
	
}
