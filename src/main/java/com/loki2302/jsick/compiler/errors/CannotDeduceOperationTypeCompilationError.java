package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.Type;

public class CannotDeduceOperationTypeCompilationError extends CompilationError {
	
	private final Type type1;
	private final Type type2;
	
	public CannotDeduceOperationTypeCompilationError(Type type1, Type type2, Object sourceContext) {
		super(sourceContext);
		this.type1 = type1;
		this.type2 = type2;
	}
	
	@Override
	public String toString() {
		return String.format("Cannot deduce common type for %s and %s", type1.getName(), type2.getName());
	}

}

