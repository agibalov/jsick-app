package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.JType;

public class OperationUndefinedForTypeCompilationError extends CompilationError {
	private final JType type;
	private final Operation operation;
	
	public OperationUndefinedForTypeCompilationError(Operation operation, JType type) {
		this.type = type;
		this.operation = operation;
	}
	
	public static enum Operation {
		Add,
		Sub,
		Mul,
		Div
	}

}
