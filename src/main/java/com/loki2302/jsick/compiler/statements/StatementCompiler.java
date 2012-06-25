package com.loki2302.jsick.compiler.statements;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.statements.AssignmentStatement;
import com.loki2302.jsick.compiler.model.statements.PrintStatement;
import com.loki2302.jsick.compiler.model.statements.Statement;
import com.loki2302.jsick.compiler.model.statements.StatementVisitor;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class StatementCompiler extends AbstractStatementCompiler<Statement> implements StatementVisitor<StatementCompilationResult> {
	
	private final AssignmentStatementCompiler assignmentCompiler;
	private final PrintStatementCompiler printStatementCompiler;
	
	public StatementCompiler(LexicalContext lexicalContext, ExpressionCompiler expressionCompiler, IntType intType, DoubleType doubleType) {
		assignmentCompiler = new AssignmentStatementCompiler(lexicalContext, expressionCompiler);
		printStatementCompiler = new PrintStatementCompiler(expressionCompiler, intType, doubleType); 
	}

	@Override
	public StatementCompilationResult compile(Statement statement) {
		return statement.accept(this);
	}

	@Override
	public StatementCompilationResult visitAssignmentStatement(AssignmentStatement statement) {
		return assignmentCompiler.compile(statement);
	}

	@Override
	public StatementCompilationResult visitPrintStatement(PrintStatement statement) {
		return printStatementCompiler.compile(statement);
	}
}
