package com.loki2302.jsick.compiler;

import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.statements.Statement;

public class StatementCompiler {
	private final StatementCompilingVisitor statementCompilingVisitor; 

	public StatementCompiler(StatementCompilingVisitor statementCompilingVisitor) {
		this.statementCompilingVisitor = statementCompilingVisitor;
	}
	
	public Statement compile(DOMStatement domStatement) {
		return domStatement.accept(statementCompilingVisitor);
	}
}
