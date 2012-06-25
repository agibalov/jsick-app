package com.loki2302.jsick.compiler.model.statements;

public interface StatementVisitor<T> {
	
	public T visitAssignmentStatement(AssignmentStatement statement);
	public T visitPrintStatement(PrintStatement statement);

}
