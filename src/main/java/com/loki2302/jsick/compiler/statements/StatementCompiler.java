package com.loki2302.jsick.compiler.statements;

import java.util.Map;

import com.loki2302.jsick.compiler.errors.UnknownStatementClassCompilationError;
import com.loki2302.jsick.compiler.model.statements.Statement;

public class StatementCompiler extends AbstractStatementCompiler<Statement> {
	
	private final Map<Class<? extends Statement>, AbstractStatementCompiler<? extends Statement>> compilersByStatementClasses;
	
	public StatementCompiler(Map<Class<? extends Statement>, AbstractStatementCompiler<? extends Statement>> compilersByStatementClasses) {
		this.compilersByStatementClasses = compilersByStatementClasses;
	}

	@Override
	public StatementCompilationResult compile(Statement statement) {
		AbstractStatementCompiler<Statement> statementCompiler = 
				(AbstractStatementCompiler<Statement>)compilersByStatementClasses.get(statement.getClass());
		if(statementCompiler == null) {
			return StatementCompilationResult.error(new UnknownStatementClassCompilationError(statement));
		}
		
		return statementCompiler.compile(statement);
	}

}
