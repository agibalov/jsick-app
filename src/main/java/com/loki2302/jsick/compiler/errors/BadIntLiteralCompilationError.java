package com.loki2302.jsick.compiler.errors;

public class BadIntLiteralCompilationError extends CompilationError {		
	
	public BadIntLiteralCompilationError(Object sourceContext) {
		super(sourceContext);
	}

	@Override
	public String toString() {
		return "Bad int literal";
	}
	
}