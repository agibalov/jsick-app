package com.loki2302.jsick.compiler;

public class SourceContextAware {

	private final Object sourceContext;
	
	protected SourceContextAware(Object sourceContext) {
		this.sourceContext = sourceContext;
	}
	
	public Object getSourceContext() {
		return sourceContext;
	}
	
}
