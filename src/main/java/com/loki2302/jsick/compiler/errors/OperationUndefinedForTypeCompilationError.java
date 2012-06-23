package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.JType;

public class OperationUndefinedForTypeCompilationError extends CompilationError {
	private final JType type;
	private final Operation operation;
	
	public OperationUndefinedForTypeCompilationError(Operation operation, JType type) {
		this.type = type;
		this.operation = operation;
	}
	
	@Override
	public String toString() {
		return String.format("Operation %s not defined for type %s", operation, type.getName());
	}
	
	public static enum Operation {
		Add,
		Sub,
		Mul,
		Div
	}

}
