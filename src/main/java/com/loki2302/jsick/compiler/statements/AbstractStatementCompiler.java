package com.loki2302.jsick.compiler.statements;

import com.loki2302.jsick.compiler.model.statements.Statement;

public abstract class AbstractStatementCompiler<S extends Statement> {
	public abstract StatementCompilationResult compile(S statement);
}
