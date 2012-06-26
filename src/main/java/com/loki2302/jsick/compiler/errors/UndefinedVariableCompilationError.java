package com.loki2302.jsick.compiler.errors;


public class UndefinedVariableCompilationError extends CompilationError {
	
	private final String variableName;
	
	public UndefinedVariableCompilationError(String variableName, Object sourceContext) {
		super(sourceContext);
		this.variableName = variableName;
	}
	
	@Override
	public String toString() {
		return String.format("Undefined variable: %s", variableName);
	}
	
}