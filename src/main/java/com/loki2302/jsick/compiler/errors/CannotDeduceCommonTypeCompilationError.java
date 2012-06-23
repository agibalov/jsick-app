package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.JType;

public class CannotDeduceCommonTypeCompilationError extends CompilationError {
	
	private final JType type1;
	private final JType type2;
	
	public CannotDeduceCommonTypeCompilationError(JType type1, JType type2, Object sourceContext) {
		super(sourceContext);
		this.type1 = type1;
		this.type2 = type2;
	}
	
	@Override
	public String toString() {
		return String.format("Cannot deduce common type for %s and %s", type1.getName(), type2.getName());
	}

}

