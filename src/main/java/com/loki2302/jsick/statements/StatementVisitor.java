package com.loki2302.jsick.statements;

public interface StatementVisitor<T> {
	T visitVariableDefinitionStatement(VariableDefinitionStatement statement);
	T visitExpressionStatement(ExpressionStatement statement);
	T visitPrintStatement(PrintStatement statement);
}