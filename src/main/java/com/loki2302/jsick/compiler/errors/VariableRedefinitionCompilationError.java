package com.loki2302.jsick.compiler.errors;


public class VariableRedefinitionCompilationError extends CompilationError {
	private final String name;
	
	public VariableRedefinitionCompilationError(String name, Object sourceContext) {
		super(sourceContext);
		this.name = name;
	}		
	
	@Override
	public String toString() {
		return String.format("Variable redefinition: %s", name);
	}
}