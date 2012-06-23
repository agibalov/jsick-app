package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.compiler.model.Statement;

public class UnknownStatementClassCompilationError extends CompilationError {
	
	private final Statement statement;
	
	public UnknownStatementClassCompilationError(Statement statement) {
		this.statement = statement;
	}
	
	@Override
	public String toString() {
		return String.format("Unknown statement class: %s", statement);
	}
}