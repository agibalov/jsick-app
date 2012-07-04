package com.loki2302.jsick.dom.statements;

public interface DOMStatementVisitor<T> {
	T visitVariableDefinitionStatement(DOMVariableDefinitionStatement statement);
	T visitExpressionStatement(DOMExpressionStatement statement);
	T visitPrintStatement(DOMPrintStatement statement);
}