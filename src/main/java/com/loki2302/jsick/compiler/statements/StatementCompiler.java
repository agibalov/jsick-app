package com.loki2302.jsick.compiler.statements;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.errors.UnknownStatementClassCompilationError;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.AssignmentStatement;
import com.loki2302.jsick.compiler.model.PrintStatement;
import com.loki2302.jsick.compiler.model.Statement;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class StatementCompiler extends AbstractStatementCompiler<Statement> {
	
	private final AssignmentStatementCompiler assignmentCompiler;
	private final PrintStatementCompiler printStatementCompiler;
	
	public StatementCompiler(LexicalContext lexicalContext, ExpressionCompiler expressionCompiler, IntType intType, DoubleType doubleType) {
		assignmentCompiler = new AssignmentStatementCompiler(lexicalContext, expressionCompiler);
		printStatementCompiler = new PrintStatementCompiler(expressionCompiler, intType, doubleType); 
	}

	@Override
	public StatementCompilationResult compile(Statement statement) {
		if(statement instanceof PrintStatement) {
			return printStatementCompiler.compile((PrintStatement)statement);
		} else if(statement instanceof AssignmentStatement) {
			return assignmentCompiler.compile((AssignmentStatement)statement);
		}
		
		return StatementCompilationResult.error(new UnknownStatementClassCompilationError());
	}
}
