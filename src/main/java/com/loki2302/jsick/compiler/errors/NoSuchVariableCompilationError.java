package com.loki2302.jsick.compiler.errors;


public class NoSuchVariableCompilationError extends CompilationError {
	
	private final String variableName;
	
	public NoSuchVariableCompilationError(String variableName, Object sourceContext) {
		super(sourceContext);
		this.variableName = variableName;
	}
	
	@Override
	public String toString() {
		return String.format("No such variable: %s", variableName);
	}
	
}