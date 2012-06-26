package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.JType;

public class CannotAssignCompilationError extends CompilationError {
	
	private final JType left;
	private final JType right;
	
	public CannotAssignCompilationError(JType left, JType right, Object sourceContext) {
		super(sourceContext);
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return String.format("Cannot assign %s to %s", right.getName(), left.getName());
	}

}

