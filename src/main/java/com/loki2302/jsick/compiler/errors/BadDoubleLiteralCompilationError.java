package com.loki2302.jsick.compiler.errors;

public class BadDoubleLiteralCompilationError extends CompilationError {	
	
	public BadDoubleLiteralCompilationError(Object sourceContext) {
		super(sourceContext);
	}

	@Override
	public String toString() {
		return "Bad double literal";
	}
	
}