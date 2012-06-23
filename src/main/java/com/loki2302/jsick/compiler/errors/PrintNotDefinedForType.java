package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.types.JType;

public class PrintNotDefinedForType extends CompilationError {
	
	private final JType type;
	
	public PrintNotDefinedForType(JType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.format("Print not defined for type %s", type.getName());
	}
	
}