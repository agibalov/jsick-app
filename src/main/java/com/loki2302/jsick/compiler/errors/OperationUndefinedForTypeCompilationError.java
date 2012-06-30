package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.Type;

public class OperationUndefinedForTypeCompilationError extends CompilationError {
	private final Type type;
	private final Operation operation;
	
	public OperationUndefinedForTypeCompilationError(Operation operation, Type type, Object sourceContext) {
		super(sourceContext);
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
