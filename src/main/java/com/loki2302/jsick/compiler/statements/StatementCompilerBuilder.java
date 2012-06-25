package com.loki2302.jsick.compiler.statements;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.compiler.model.statements.Statement;

public class StatementCompilerBuilder {		
	private final Map<Class<? extends Statement>, AbstractStatementCompiler<? extends Statement>> 
		compilersByStatementClasses = 
			new HashMap<Class<? extends Statement>, AbstractStatementCompiler<? extends Statement>>();
	
	public <S extends Statement> StatementCompilerBuilder registerCompiler(
			Class<S> statementClass, 
			AbstractStatementCompiler<S> statementCompiler) {
		compilersByStatementClasses.put(statementClass, statementCompiler);
		return this;
	}
	
	public StatementCompiler build() {
		return new StatementCompiler(compilersByStatementClasses);
	}
}