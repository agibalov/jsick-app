package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.Type;

public class PrintNotDefinedForType extends CompilationError {
	
	private final Type type;
	
	public PrintNotDefinedForType(Type type, Object sourceContext) {
		super(sourceContext);
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.format("Print not defined for type %s", type.getName());
	}
	
}