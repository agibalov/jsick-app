package com.loki2302.jsick.compiler.errors;


public class UnknownSimpleTypeCompilationError extends CompilationError {
	private final String typeName;
	
	public UnknownSimpleTypeCompilationError(String typeName, Object sourceContext) {
		super(sourceContext);
		this.typeName = typeName;
	}
	
	@Override
	public String toString() {
		return String.format("Unknown type: %s", typeName);
	}
}