package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.Type;

public class CannotAssignCompilationError extends CompilationError {
	
	private final Type left;
	private final Type right;
	
	public CannotAssignCompilationError(Type left, Type right, Object sourceContext) {
		super(sourceContext);
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return String.format("Cannot assign %s to %s", right.getName(), left.getName());
	}

}

